package com.example.student.account;

import java.math.BigDecimal;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class TransferRequest {

    @NotNull(message = "转出账户不能为空")
    private Integer fromId;

    @NotNull(message = "转入账户不能为空")
    private Integer toId;

    @NotNull(message = "转账金额不能为空")
    @DecimalMin(
            value = "0.01",
            message = "转账金额必须大于0"
    )
    private BigDecimal amount;

    public TransferRequest() {
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}