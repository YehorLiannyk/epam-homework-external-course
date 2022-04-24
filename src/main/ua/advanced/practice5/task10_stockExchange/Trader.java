package main.ua.advanced.practice5.task10_stockExchange;

import java.util.HashMap;
import java.util.Map;

public class Trader implements Runnable {
    private final String name;
    private Map<Stock, Integer> boughtStocks; // K: Stock, V: Amount of the stock
    private final TraderStockHelper helper = new TraderStockHelper();

    public Trader(String name) {
        this.name = name;
        boughtStocks = new HashMap<>();
    }

    @Override
    public void run() {
        Stock stock = helper.whatStockToBuyOrSell();
        int amount;
        String traderAction;
        if (!boughtStocks.containsKey(stock) || helper.isBuyingEitherSelling()) {
            traderAction = "bought";
            amount = getAmountForBuying(stock);
        } else {
            traderAction = "sold";
            amount = getAmountForSelling(stock);
        }
        System.out.println("Trader " + name + " " + traderAction + " " + amount + " " + stock.getName() +
                " stocks, currentPrice: " + StockExchange.getStocks().get().get(stock) + ", startPrice: " + stock.getStartPrice());

    }

    private int getAmountForSelling(Stock stock) {
        int amount;
        amount = helper.getRandomAmountOfStockForSelling(stock, this);
        helper.sellStock(this, stock, amount);
        return amount;
    }

    private int getAmountForBuying(Stock stock) {
        int amount;
        amount = helper.getRandomAmountOfStockForBuying(stock);
        helper.buyStock(this, stock, amount);
        return amount;
    }

    public String getName() {
        return name;
    }

    public void addStocks(Stock stock, int amount) {
        if (boughtStocks.containsKey(stock)) {
            int oldAmount = boughtStocks.get(stock);
            boughtStocks.replace(stock, oldAmount, (oldAmount + amount));
        } else {
            boughtStocks.put(stock, amount);
        }
    }

    public void removeStocks(Stock stock, int amount) {
        if (boughtStocks.containsKey(stock)) {
            int oldAmount = boughtStocks.get(stock);
            int newAmount;
            removingStonks(stock, amount, oldAmount);
        } else {
            boughtStocks.put(stock, amount);
        }
    }

    private void removingStonks(Stock stock, int amount, int oldAmount) {
        int newAmount;
        if (oldAmount - amount > 0) {
            newAmount = oldAmount - amount;
            boughtStocks.replace(stock, oldAmount, newAmount);
        } else if (oldAmount - amount == 0) {
            boughtStocks.remove(stock);
        } else {
            throw new IllegalStateException("Subtract stocks more than Trader had");
        }
    }

    public Map<Stock, Integer> getBoughtStocks() {
        return boughtStocks;
    }

    public void setBoughtStocks(Map<Stock, Integer> boughtStocks) {
        this.boughtStocks = boughtStocks;
    }
}
