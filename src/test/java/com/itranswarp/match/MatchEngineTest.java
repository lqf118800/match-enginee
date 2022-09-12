package com.itranswarp.match;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class MatchEngineTest {

    long sequenceId = 0;

    @Test
    public void testMatchEngine() {
        String inputs = """
                buy  202.34 1
                sell 207.6  2
                buy  207.8  3
                buy  205.01 5
                sell 208.02 3
                sell 207.60 5
                buy  201.11 7
                buy  206.0  3
                buy  208.33 5
                sell 206.54 3
                sell 206.55 5
                buy  206.55 3
                """;
        MatchEngine engine = new MatchEngine();
        for (String input : inputs.strip().lines().toArray(String[]::new)) {
            Order order = createOrder(input);
            System.out.println("\n================================================================================\n");
            System.out.println("process order: " + order);
            MatchResult result = engine.processOrder(order);
            System.out.println(engine);
            System.out.println(result);
        }
    }

    Order createOrder(String input) {
        sequenceId++;
        String[] ss = input.split("\\s+");
        Direction direction = Direction.valueOf(ss[0].toUpperCase());
        BigDecimal price = new BigDecimal(ss[1]);
        BigDecimal amount = new BigDecimal(ss[2]);
        return new Order(sequenceId, direction, price, amount);
    }
}
