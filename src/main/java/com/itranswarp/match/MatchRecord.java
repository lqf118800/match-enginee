package com.itranswarp.match;

import java.math.BigDecimal;

public class MatchRecord {

    public final BigDecimal price;
    public final BigDecimal amount;
    public final Order insertOrder;
    public final Order makerOrder;

    public MatchRecord(BigDecimal price, BigDecimal amount, Order insertOrder, Order makerOrder) {
        this.price = price;
        this.amount = amount;
        this.insertOrder = insertOrder;
        this.makerOrder = makerOrder;
    }

    @Override
    public String toString() {
        return String.format("[%.2f, %.2f]", this.price, this.amount);
    }
}
