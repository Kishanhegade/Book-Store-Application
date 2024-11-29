package com.bridgelabz.bsa.controller;

import com.bridgelabz.bsa.dto.OrderRequest;
import com.bridgelabz.bsa.dto.OrderResponse;
import com.bridgelabz.bsa.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @PostMapping("/orders/place-order")
    public ResponseEntity<OrderResponse> placeOrder(OrderRequest orderRequest) {

    }

    public ResponseEntity<OrderResponse> cancelOrder(@PathVariable long orderId) {

    }

    @GetMapping("")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {

    }

    @GetMapping("")
    public ResponseEntity<List<OrderResponse>> getAllOrdersForUser(@RequestHeader("Authorization") String authHeader) {

    }


}
