package main.ua.advanced.practice6.startegy.cards;

public class CardClass implements Card{
    private final String cardName;

    public CardClass(String cardName) {
        this.cardName = cardName;
    }

    public CardClass(int number) {
        this(Integer.toString(number));
    }

    @Override
    public String name() {
        return cardName;
    }
}
