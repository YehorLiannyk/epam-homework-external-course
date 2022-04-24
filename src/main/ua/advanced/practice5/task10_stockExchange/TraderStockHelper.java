package main.ua.advanced.practice5.task10_stockExchange;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import static main.ua.advanced.practice5.task10_stockExchange.StockExchangeDefaults.*;

public class TraderStockHelper {
    public Stock whatStockToBuyOrSell() {
        final int maxRange = STOCK_AMOUNT;
        int indexOfStock = (int) (Math.random() * maxRange);
        return getStockFromHashMapByArrIndex(indexOfStock);
    }

    private Stock getStockFromHashMapByArrIndex(int indexOfStock) {
        Set<Stock> set = StockExchange.getStocks().get().keySet();
        for (var item : set) {
            if (item.getName().equals(stockArray[indexOfStock].getName())) {
                return item;
            }
        }
        return null;
    }

    public boolean isBuyingEitherSelling() {
        return Math.random() < PROBABILITY_OF_BUYING_EITHER_SELLING;
    }

    public int getRandomAmountOfStockForBuying(Stock stock) {
        return (int) (Math.random() * (stock.getCurrentAmount().get() / RANDOM_STOCK_AMOUNT_COEFFICIENT));
    }

    public int getRandomAmountOfStockForSelling(Stock stock, Trader trader) {
        return (int) (Math.random() * trader.getBoughtStocks().get(stock));
    }

    public boolean buyStock(Trader trader, Stock stock, int amount) {
        changeStockWhenBuying(stock, amount);
        trader.addStocks(stock, amount);
        return true;
    }

    public boolean sellStock(Trader trader, Stock stock, int amount) {
        changeStockWhenSelling(stock, amount);
        trader.removeStocks(stock, amount);
        return true;
    }

    private void changeStockWhenBuying(Stock stock, int amount) {
        final AtomicReference<Map<Stock, BigDecimal>> stockMap = StockExchange.getStocks();
        BigDecimal newPrice = getNewPrice(stockMap, stock, amount, true);
        stockMap.get().replace(stock, newPrice);
    }

    private void changeStockWhenSelling(Stock stock, int amount) {
        final AtomicReference<Map<Stock, BigDecimal>> stockMap = StockExchange.getStocks();
        BigDecimal newPrice = getNewPrice(stockMap, stock, amount, false);
        stockMap.get().replace(stock, newPrice);
    }


    private BigDecimal getNewPrice(AtomicReference<Map<Stock, BigDecimal>> map, Stock stock, int amount, boolean isBuying) {
        if (isBuying) {
            final BigDecimal oneStock = stock.getStartPrice().multiply(BUY_STEP);
            final BigDecimal allStock = oneStock.multiply(BigDecimal.valueOf(amount));
            return map.get().get(stock).add(allStock);
        } else {
            final BigDecimal oneStock = stock.getStartPrice().multiply(SELL_STEP);
            final BigDecimal allStock = oneStock.multiply(BigDecimal.valueOf(amount));
            return getNewPriceAfterComparing(map, stock, allStock);
        }
    }

    private BigDecimal getNewPriceAfterComparing(AtomicReference<Map<Stock, BigDecimal>> map, Stock stock, BigDecimal allStock) {
        if (allStock.compareTo(map.get().get(stock)) > 0)
            return map.get().replace(stock, BigDecimal.valueOf(0));
        else
            return map.get().get(stock).subtract(allStock);
    }
}
