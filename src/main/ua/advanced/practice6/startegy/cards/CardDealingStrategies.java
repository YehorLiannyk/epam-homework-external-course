package main.ua.advanced.practice6.startegy.cards;

import main.ua.advanced.practice6.startegy.cards.DealingStrategies.BridgeCardDealingStrategy;
import main.ua.advanced.practice6.startegy.cards.DealingStrategies.ClassicPokerCardDealingStrategy;
import main.ua.advanced.practice6.startegy.cards.DealingStrategies.FoolCardDealingStrategy;
import main.ua.advanced.practice6.startegy.cards.DealingStrategies.TexasHoldemCardDealingStrategy;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CardDealingStrategies {
    public static final Logger logger = Logger.getLogger(CardDealingStrategies.class.getName());
    //just for demonstration
    static {
        try {
            Handler fileHandler = new FileHandler("resources/advanced/practice6/" + "card_deal_strategy.log");
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CardDealingStrategy texasHoldemCardDealingStrategy() {
        return new TexasHoldemCardDealingStrategy();
    }

    public static CardDealingStrategy classicPokerCardDealingStrategy() {
        return new ClassicPokerCardDealingStrategy();
    }

    public static CardDealingStrategy bridgeCardDealingStrategy() {
        return new BridgeCardDealingStrategy();
    }

    public static CardDealingStrategy foolCardDealingStrategy() {
        return new FoolCardDealingStrategy();
    }

}
