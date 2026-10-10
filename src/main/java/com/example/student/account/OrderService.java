package com.example.student.account;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService){
        this.paymentService=paymentService;
    }

    @Transactional
    public void createOrder(){
        paymentService.decreaseAlice();

        throw new RuntimeException("模拟订单保存失败");
    }
}
