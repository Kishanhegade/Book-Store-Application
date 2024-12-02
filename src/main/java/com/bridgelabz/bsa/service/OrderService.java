package com.bridgelabz.bsa.service;

import com.bridgelabz.bsa.requestdto.OrderRequest;
import com.bridgelabz.bsa.responsedto.OrderResponse;
import com.bridgelabz.bsa.exception.CartNotFoundByIdException;
import com.bridgelabz.bsa.exception.InvalidQuantityException;
import com.bridgelabz.bsa.exception.OrderNotFoundByIdException;
import com.bridgelabz.bsa.exception.UserNotFoundByIdException;
import com.bridgelabz.bsa.mapper.OrderMapper;
import com.bridgelabz.bsa.model.*;
import com.bridgelabz.bsa.repository.OrderRepository;
import com.bridgelabz.bsa.repository.UserRepository;
import com.bridgelabz.bsa.security.JwtUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepo;
    private UserRepository userRepo;
    private CartService cartService;
    private BookService bookService;
    private OrderMapper orderMapper;
    private JwtUtils jwtUtils;


    @Transactional
    public OrderResponse placeOrderByUser(String token, OrderRequest orderRequest) {
        User user = fetchUserByToken(token);

        List<Cart> carts = cartService.findAllByUserId(user.getUserId());

        double totalPrice = carts.stream().mapToDouble(cart -> {
            Book book = cart.getBook();
            if (cart.getQuantity() > book.getQuantity()) {
                throw new InvalidQuantityException("Not enough stock for book: " + book.getBookName()+ " in cart " + cart.getCartId());
            }
            return cart.getTotalPrice();
        }).sum();

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDate.now());
        order.setPrice(totalPrice);
        order.setAddress(orderRequest.getAddress());
        order.setCancel(false);

        List<OrderBook> orderBooks = carts.stream().map(cart -> {
            Book book = cart.getBook();
            book.setQuantity(book.getQuantity() - cart.getQuantity());
            bookService.updateBook(book);

            order.setQuantity(cart.getQuantity());

            OrderBook orderBook = new OrderBook();
            orderBook.setOrder(order);
            orderBook.setBook(book);
            orderBook.setQuantity(cart.getQuantity());
            return orderBook;
        }).toList();

        order.setOrderBooks(orderBooks);

        Order savedOrder = orderRepo.save(order);

        cartService.removeFromCartByUserId(user.getUserId());

        return orderMapper.mapToOrderResponse(savedOrder);
    }


    @Transactional
    public OrderResponse placeOrderByCartId(String token, long cartId, OrderRequest orderRequest) {
        User user = fetchUserByToken(token);
        Cart cart = cartService.findByCartId(cartId)
                .orElseThrow(() -> new CartNotFoundByIdException("Cart item not found"));

        Book book = cart.getBook();
        if (cart.getQuantity() > book.getQuantity()) {
            throw new InvalidQuantityException("Not enough stock for book: " + book.getBookName()+ " in cart " + cart.getCartId());
        }

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDate.now());
        order.setPrice(cart.getTotalPrice());
        order.setAddress(orderRequest.getAddress());
        order.setCancel(false);
        order.setQuantity(cart.getQuantity());
        OrderBook orderBook = new OrderBook();
        orderBook.setOrder(order);
        orderBook.setBook(book);
        orderBook.setQuantity(cart.getQuantity());
        order.setOrderBooks(List.of(orderBook));

        book.setQuantity(book.getQuantity() - cart.getQuantity());
        bookService.updateBook(book);

        order = orderRepo.save(order);

        cartService.removeFromCartByCartId(cartId);
        return orderMapper.mapToOrderResponse(order);
    }

    @Transactional
    public OrderResponse cancelOrder(long orderId) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundByIdException("Order not found"));

        if (order.isCancel()) {
            throw new RuntimeException("Order is already canceled");
        }

        order.getOrderBooks().forEach(orderBook -> {
            Book book = orderBook.getBook();
            book.setQuantity(book.getQuantity() + orderBook.getQuantity());
            bookService.updateBook(book);
        });

        order.setCancel(true);
        orderRepo.save(order);

        return orderMapper.mapToOrderResponse(order);
    }

    public List<OrderResponse> getAllOrders() {
        return orderRepo.findAll().stream()
                .map(orderMapper::mapToOrderResponse)
                .toList();
    }


    public List<OrderResponse> getAllOrdersForUser(String token) {
        long userId = jwtUtils.extractUserIdFromToken(token);
        return orderRepo.findByUserId(userId).stream()
                .map(orderMapper::mapToOrderResponse)
                .toList();
    }

    private User fetchUserByToken(String token) {
        long userId = jwtUtils.extractUserIdFromToken(token);
        return userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundByIdException("User not found"));
    }
}
