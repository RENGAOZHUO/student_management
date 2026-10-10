package com.example.student.account;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TransactionTestController {

    private final OrderService orderService;

    public TransactionTestController(OrderService orderService){
        this.orderService=orderService;
    }

    @PostMapping("/required")
    public ResponseEntity<Void> testRequired(){

        orderService.createOrder();

        return ResponseEntity.noContent().build();
    }
}
