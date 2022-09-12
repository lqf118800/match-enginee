package com.itranswarp.match;

import java.math.BigDecimal;

public class MatchEngine {

    public final OrderBook buyBook = new OrderBook(Direction.BUY);
    public final OrderBook sellBook = new OrderBook(Direction.SELL);
    public BigDecimal marketPrice = BigDecimal.ZERO; // 最新市场价

    public MatchResult processOrder(Order order) {
        switch (order.direction) {
        case BUY:
            return processOrder(order, this.sellBook, this.buyBook);
        case SELL:
            return processOrder(order, this.buyBook, this.sellBook);
        default:
            throw new IllegalArgumentException("Invalid direction.");
        }
    }

    /**
     * @param insertOrder  输入订单
     * @param makerBook   尝试匹配成交的OrderBook
     * @param anotherBook 未能完全成交后挂单的OrderBook
     * @return 成交结果
     */
    MatchResult processOrder(Order insertOrder, OrderBook makerBook, OrderBook anotherBook) {
        MatchResult matchResult = new MatchResult(insertOrder);
        for (;;) {
            Order makerOrder = makerBook.getFirst();
            if (makerOrder == null) {
                // No match order
                break;
            }
            if (insertOrder.direction == Direction.BUY && insertOrder.price.compareTo(makerOrder.price) < 0) {
                // The buy order price is lower than the first price of the sell order
                break;
            } else if (insertOrder.direction == Direction.SELL && insertOrder.price.compareTo(makerOrder.price) > 0) {
                // The sell order price is higher than the first price of the buy order
                break;
            }
            // Deal at Maker price
            this.marketPrice = makerOrder.price;
            // The number of deals is the smaller of the two
            BigDecimal matchedAmount = insertOrder.amount.min(makerOrder.amount);
            // match record
            matchResult.add(makerOrder.price, matchedAmount, makerOrder);
            // Update the number of orders after a deal
            insertOrder.amount = insertOrder.amount.subtract(matchedAmount);
            makerOrder.amount = makerOrder.amount.subtract(matchedAmount);
            // After the opponent order is fully filled, it is removed from the order book
            if (makerOrder.amount.signum() == 0) {
                makerBook.remove(makerOrder);
            }
            if (insertOrder.amount.signum() == 0) {
                break;
            }
        }
        // When a Taker order is not fully filled, it is placed in the order book
        if (insertOrder.amount.signum() > 0) {
            anotherBook.add(insertOrder);
        }
        return matchResult;
    }

    public boolean cancel(Order order) {
        OrderBook book = order.direction == Direction.BUY ? this.buyBook : this.sellBook;
        return book.remove(order);
    }

    @Override
    public String toString() {
        String line = "\n-------------------------\n";
        return line + this.sellBook + line + this.marketPrice + line + this.buyBook + line;
    }
}
