package main.ua.advanced.practice6.startegy.cards;

import main.ua.advanced.practice6.startegy.cards.DealingStrategies.BridgeCardDealingStrategy;
import main.ua.advanced.practice6.startegy.cards.DealingStrategies.ClassicPokerCardDealingStrategy;
import main.ua.advanced.practice6.startegy.cards.DealingStrategies.FoolCardDealingStrategy;
import main.ua.advanced.practice6.startegy.cards.DealingStrategies.TexasHoldemCardDealingStrategy;

public class CardDealingStrategies {

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
