package com.bridgelabz.bsa.service;

import com.bridgelabz.bsa.dto.OrderRequest;
import com.bridgelabz.bsa.dto.OrderResponse;
import com.bridgelabz.bsa.mapper.OrderMapper;
import com.bridgelabz.bsa.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    public OrderResponse placeOrder(String token, OrderRequest orderRequest) {

    }

    public OrderResponse cancelOrder(String token) {
    }

    public List<OrderResponse> getAllOrders() {
    }

    public List<OrderResponse> getAllOrdersForUser(String token) {
    }
}
