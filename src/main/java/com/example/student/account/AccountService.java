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

    @Transactional
    public void transfer(
            int fromId,
            int toId,
            BigDecimal amount) {

        // 1. 基本参数检查
        if (fromId == toId) {
            throw new IllegalArgumentException("不能给自己转账");
        }

        if (amount == null ||
                amount.compareTo(BigDecimal.ZERO) <= 0) {

            throw new IllegalArgumentException("转账金额必须大于0");
        }

        // 2. 决定统一的加锁顺序
        int firstId = Math.min(fromId, toId);
        int secondId = Math.max(fromId, toId);

        // 3. 按固定顺序锁两个账户
        BigDecimal firstBalance =
                accountDao.findBalanceForUpdate(firstId);

        BigDecimal secondBalance =
                accountDao.findBalanceForUpdate(secondId);

        // 4. 检查账户是否存在
        if (firstBalance == null || secondBalance == null) {
            throw new IllegalArgumentException("账户不存在");
        }

        // 5. 找到真正的转出账户余额
        BigDecimal fromBalance;

        if (fromId == firstId) {
            fromBalance = firstBalance;
        } else {
            fromBalance = secondBalance;
        }

        // 6. 检查余额
        if (fromBalance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("余额不足");
        }

        // 7. 扣款
        int decreaseRows =
                accountDao.decreaseBalance(fromId, amount);

        // 8. 入账
        int increaseRows =
                accountDao.increaseBalance(toId, amount);

        // 9. 检查数据库操作结果
        if (decreaseRows != 1 || increaseRows != 1) {
            throw new IllegalStateException("转账失败");
        }
    }
}


