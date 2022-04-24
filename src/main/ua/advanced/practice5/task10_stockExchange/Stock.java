package main.ua.advanced.practice5.task10_stockExchange;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

public class Stock {
    private final String name;
    private final BigDecimal startPrice;
    private final int generalStockAmount;
    private AtomicInteger currentAmount;

    public Stock(String name, BigDecimal startPrice, int generalStockAmount) {
        this.name = name;
        this.startPrice = startPrice;
        this.generalStockAmount = generalStockAmount;
        this.currentAmount = new AtomicInteger(generalStockAmount);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public int getGeneralStockAmount() {
        return generalStockAmount;
    }

    public AtomicInteger getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(AtomicInteger currentAmount) {
        this.currentAmount = currentAmount;
    }
}
