package com.example.student.account;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class AccountDao {

    private final JdbcTemplate jdbcTemplate;

    public AccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public BigDecimal findBalanceForUpdate(int id) {

        String sql = """
                SELECT balance
                FROM account
                WHERE id = ?
                FOR UPDATE
                """;

        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    BigDecimal.class,
                    id
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int decreaseBalance(int id, BigDecimal amount) {

        String sql = """
                UPDATE account
                SET balance = balance - ?
                WHERE id = ?
                """;

        return jdbcTemplate.update(sql, amount, id);
    }

    public int increaseBalance(int id, BigDecimal amount) {

        String sql = """
                UPDATE account
                SET balance = balance + ?
                WHERE id = ?
                """;

        return jdbcTemplate.update(sql, amount, id);
    }
    // Day59故意制造数据库错误
    /*public void brokenUpdate(int id) {
        String sql = """
                UPDATE account
                SET not_exist_column = 100
                WHERE id = ?
                """;

        jdbcTemplate.update(sql, id);
    }*/
}
