package main.ua.advanced.practice6.startegy.cards;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DeckClass implements Deck{

    LinkedList<Card> cards;

    public DeckClass(final int cardsAmount) {
        this.cards = new LinkedList<>();
        for (int i = 0; i < cardsAmount; i++) {
            cards.push(new CardClass(i));
        }
    }

    @Override
    public Card dealCard() {
        return cards.isEmpty() ? null : cards.pop();
    }

    @Override
    public List<Card> restCards() {
        final ArrayList<Card> rest = new ArrayList<>(this.cards);
        cards.clear();
        return rest;
    }

    @Override
    public int size() {
        return cards.size();
    }

}
