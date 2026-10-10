package com.example.student.account;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class PaymentService {

    private final AccountDao accountDao;

    public PaymentService(AccountDao accountDao){
        this.accountDao=accountDao;
    }

    @Transactional
    public void decreaseAlice(){

        accountDao.decreaseBalance(
                1,
                new BigDecimal("100.00")
        );
    }
}
