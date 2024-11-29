package com.bridgelabz.bsa.controller;

import com.bridgelabz.bsa.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;
}
