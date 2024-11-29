package com.bridgelabz.bsa.controller;

import com.bridgelabz.bsa.dto.OrderRequest;
import com.bridgelabz.bsa.dto.OrderResponse;
import com.bridgelabz.bsa.service.OrderService;
import jakarta.validation.Valid;
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
    public ResponseEntity<OrderResponse> placeOrderByUser(@RequestHeader("Authorization") String authHeader,@Valid OrderRequest orderRequest) {
        String token = authHeader.substring(7);
        OrderResponse orderResponse = orderService.placeOrderByUser(token, orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }

    @PostMapping("/orders/place/{cartId}")
    public ResponseEntity<OrderResponse> placeOrderByCartId(@RequestHeader("Authorization") String authHeader,@PathVariable long cartId,@Valid OrderRequest orderRequest) {
        String token = authHeader.substring(7);
        OrderResponse orderResponse = orderService.placeOrderByCartId(token, cartId, orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }

    @PatchMapping("/orders/{orderId}/cancel")
    public ResponseEntity<OrderResponse> cancelOrder(@RequestHeader("Authorization") String authHeader, @PathVariable long orderId) {
        OrderResponse orderResponse = orderService.cancelOrder(orderId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderResponse);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> orderResponses = orderService.getAllOrders();
        return ResponseEntity.status(HttpStatus.OK).body(orderResponses);
    }

    @GetMapping("/orders/user")
    public ResponseEntity<List<OrderResponse>> getAllOrdersForUser(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        List<OrderResponse> orderResponses = orderService.getAllOrdersForUser(token);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponses);
    }


}
