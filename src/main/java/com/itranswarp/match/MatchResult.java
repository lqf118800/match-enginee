package com.itranswarp.match;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MatchResult {

    public final Order insertOrder;
    public final List<MatchRecord> matchRecords = new ArrayList<>();

    public MatchResult(Order insertOrder) {
        this.insertOrder = insertOrder;
    }

    public void add(BigDecimal price, BigDecimal matchedAmount, Order makerOrder) {
        matchRecords.add(new MatchRecord(price, matchedAmount, this.insertOrder, makerOrder));
    }

    @Override
    public String toString() {
        if (matchRecords.isEmpty()) {
            return "no matched.";
        }
        return matchRecords.size() + " matched: " + String.join(", ", this.matchRecords.stream().map(MatchRecord::toString).toArray(String[]::new));
    }
}
