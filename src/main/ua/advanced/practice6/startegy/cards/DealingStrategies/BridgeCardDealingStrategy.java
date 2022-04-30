package main.ua.advanced.practice6.startegy.cards.DealingStrategies;

import main.ua.advanced.practice6.startegy.cards.Card;
import main.ua.advanced.practice6.startegy.cards.CardDealingStrategies;
import main.ua.advanced.practice6.startegy.cards.CardDealingStrategy;
import main.ua.advanced.practice6.startegy.cards.Deck;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;

public class BridgeCardDealingStrategy implements CardDealingStrategy {
    private final int CARDS_AMOUNT_FOR_PLAYER = 13;
    private Map<String, List<Card>> stack;

    @Override
    public Map<String, List<Card>> dealStacks(Deck deck, int players) {
        stack = new TreeMap<>();
        stack.putAll(generatePlayersOnlyMap(deck, players));
        CardDealingStrategies.logger.log(Level.INFO,"Stack now: " + stack);
        return stack;
    }

    private Map<String, List<Card>> generatePlayersOnlyMap(Deck deck, int players) {
        Map<String, List<Card>> playersOnly = new TreeMap<>();
        for (int i = 1; i <= players; i++) {
            playersOnly.put("Player " + i, new LinkedList<>());
        }
        //роздача карт по одній за раунд
        for (int i = 0; i < CARDS_AMOUNT_FOR_PLAYER; i++) {
            playersOnly.forEach((player, cards) -> cards.add(deck.dealCard()));
        }
        return playersOnly;
    }

}
