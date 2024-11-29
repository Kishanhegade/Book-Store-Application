package com.bridgelabz.bsa.controller;

import com.bridgelabz.bsa.dto.OrderRequest;
import com.bridgelabz.bsa.dto.OrderResponse;
import com.bridgelabz.bsa.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @PostMapping("/orders/place")
    public ResponseEntity<OrderResponse> placeOrder(@RequestHeader("Authorization") String authHeader, OrderRequest orderRequest) {
        String token = authHeader.substring(7);
        OrderResponse orderResponse = orderService.placeOrder(token, orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }

    @PatchMapping("/orders/cancel")
    public ResponseEntity<OrderResponse> cancelOrder(@RequestHeader("Authorization") String authHeader, @PathVariable long orderId) {
        String token = authHeader.substring(7);
        OrderResponse orderResponse = orderService.cancelOrder(token);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }

    @GetMapping("")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> orderResponses = orderService.getAllOrders();
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponses);
    }

    @GetMapping("")
    public ResponseEntity<List<OrderResponse>> getAllOrdersForUser(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        List<OrderResponse> orderResponses = orderService.getAllOrdersForUser(token);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponses);
    }


}
