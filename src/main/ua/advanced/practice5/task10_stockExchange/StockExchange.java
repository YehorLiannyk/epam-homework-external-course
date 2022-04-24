package main.ua.advanced.practice5.task10_stockExchange;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static main.ua.advanced.practice5.task10_stockExchange.StockExchangeDefaults.*;

public class StockExchange {
    private static final AtomicReference<Map<Stock, BigDecimal>> stocks = getFilledHashMap(); // K: Stock, V: currentPrice
    private static final ExecutorService executorService = Executors.newFixedThreadPool(TRADER_AMOUNT);
    private static final BigDecimal BASIC_STOCK_INDEX = findStockIndex();
    private static final BigDecimal CRITICAL_STOCK_INDEX = BASIC_STOCK_INDEX.divideToIntegralValue(BigDecimal.valueOf(2), mathContext);
    private final AtomicBoolean loopIsBroken = new AtomicBoolean(false);

    public static void main(String[] args) {
        StockExchange stockExchange = new StockExchange();
        stockExchange.startStocks();
    }

    private static BigDecimal findStockIndex() {
        AtomicReference<BigDecimal> sum = new AtomicReference<>(new BigDecimal(0));
        stocks.get().forEach((stock, price) -> sum.updateAndGet(v -> v.add(price)));
        final BigDecimal generalAmount = BigDecimal.valueOf(stocks.get().size());
        return sum.get().divideToIntegralValue(generalAmount, mathContext);
    }

    private static AtomicReference<Map<Stock, BigDecimal>> getFilledHashMap() {
        Map<Stock, BigDecimal> map = new HashMap<>(STOCK_AMOUNT);
        for (int i = 0; i < STOCK_AMOUNT; i++) {
            map.put(stockArray[i], stockArray[i].getStartPrice());
        }
        return new AtomicReference<>(map);
    }

    public static AtomicReference<Map<Stock, BigDecimal>> getStocks() {
        return stocks;
    }

    private void startStocks() {
        seekerThread();
        submitThreads();
    }

    private void seekerThread() {
        ExecutorService seeker = Executors.newSingleThreadExecutor();
        seeker.execute(() -> {
            while (true) {
                if (seekerThreadFunction()) break;
            }
            seeker.shutdown();
        });
    }

    private boolean seekerThreadFunction() {
        try {
            final BigDecimal stockIndex = findStockIndex();
            if (CRITICAL_STOCK_INDEX.compareTo(stockIndex) >= 0) {
                loopIsBroken.set(true);
                return true;
            }
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void submitThreads() {
        while (!loopIsBroken.get()) {
            loopThreadFunction();
        }
        executorService.shutdownNow();
        System.out.println("Selling was stopped because of Stock Index had a strong fall");
        System.out.println("Basic Index: " + BASIC_STOCK_INDEX + ", current Index: " + findStockIndex());
    }

    private void loopThreadFunction() {
        Future<Trader> traderFuture;
        for (int i = 0; i < TRADER_AMOUNT; i++) {
            traderFuture = (Future<Trader>) executorService.submit(TRADERS[i]);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
