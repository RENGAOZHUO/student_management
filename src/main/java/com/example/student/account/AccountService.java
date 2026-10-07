package com.example.student.account;
import com.example.student.account.AccountDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountService {

    private final AccountDao accountDao;

    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void transferWithoutTransaction() {

        accountDao.decreaseBalance(
                1,
                new BigDecimal("100.00")
        );

        accountDao.brokenUpdate(2);
    }

    @Transactional
    public void transferWithTransaction() {

        accountDao.decreaseBalance(
                1,
                new BigDecimal("100.00")
        );

        throw new RuntimeException("模拟转账过程中程序异常");
    }
}


