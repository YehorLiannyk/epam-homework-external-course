package main.ua.advanced.practice5.task10_stockExchange;

import java.math.BigDecimal;
import java.math.MathContext;

public interface StockExchangeDefaults {
    Stock[] stockArray = new Stock[]{
            new Stock("Apple", BigDecimal.valueOf(162), 82_000),
            new Stock("Microsoft", BigDecimal.valueOf(274), 29_000),
            new Stock("Tesla", BigDecimal.valueOf(1005), 22_000)
    };
    int STOCK_AMOUNT = stockArray.length;

    Trader[] TRADERS = new Trader[]{
            new Trader("Peter Schiff"),
            new Trader("George Soros"),
            new Trader("Jesse Livermore"),
            new Trader("Simon Cawkwell"),
            new Trader("Paul Tudor Jones")
    };
    int TRADER_AMOUNT = TRADERS.length;

    MathContext mathContext = new MathContext(17);

    // is so low for showing stop function when Index fell
    double PROBABILITY_OF_BUYING_EITHER_SELLING = 0.1;

    // formula: amount = (Math.random() * (stock.getCurrentAmount().get() / RANDOM_STOCK_AMOUNT_COEFFICIENT)
    int RANDOM_STOCK_AMOUNT_COEFFICIENT = 8;

    // coefficient which multiplies the price when buying/selling
    BigDecimal BUY_STEP = BigDecimal.valueOf(0.0001);
    BigDecimal SELL_STEP = BigDecimal.valueOf(0.0002);
}
