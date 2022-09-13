Match Engine
===========================

# Zerologix coding assignment
Imagine you have a trade engine that accepts orders. Any protocol or inside program triggering is
acceptable. An order request at least has this information (buy or sell, quantity, market price, or limit
price).
The engine matches buy and sell orders that have the same price. Orders have the same price
determined by their timestamp (FIFO). Pending orders queue up in your system until they are filled or
killed. Your system will have multiple traders executing orders at the same time.


****

| Coder    | Jackie                   |
|----------|--------------------------
| Linkedin | https://www.linkedin.com/in/jackiechen1995
               


****

## Available Scripts

To run this project: 

Find the file in directory: './src/test/MatchEngineTest'. 

Simply run this file and the matching engine will run automatically in the console.

****


Principle:

In the trading market, matchmaking transaction is a micro price discovery model, 
which allows buyers and sellers to submit buy and sell orders and quotations, 
in the order of price priority and time priority, when the buy order price is greater than or 
equal to the sell order price, the two parties reach a price negotiation and transaction.

For the matchmaking engine, it must maintain two lists of orders, one buy order and one sell order, 
and the buy order is sorted from high to low by price, ensuring that the order with the highest 
quotation is ranked first; Sell orders, on the other hand, are sorted from lowest to highest price, 
ensuring that the sell order with the lowest price is at the top.








