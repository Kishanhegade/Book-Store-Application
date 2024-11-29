package com.bridgelabz.bsa.mapper;

import com.bridgelabz.bsa.dto.OrderResponse;
import com.bridgelabz.bsa.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderResponse mapToOrderResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderId(order.getOrderId());
        orderResponse.setUserId(order.getUser().getUserId());
        orderResponse.setBookId(order.getBook().getBookId());
        orderResponse.setOrderDate(order.getOrderDate());
        orderResponse.setPrice(order.getPrice());
        orderResponse.setAddress(order.getAddress());
        orderResponse.setQuantity(order.getQuantity());
        return orderResponse;
    }
}
