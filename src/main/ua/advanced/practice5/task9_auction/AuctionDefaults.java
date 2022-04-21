package main.ua.advanced.practice5.task9_auction;

public interface AuctionDefaults {
    int MEMBER_AMOUNT = 5;
    String[] MEMBER_NAMES = {"Max", "Taras", "Svyatoslav", "Vasyl", "Yehor", "Yaroslav"};

    int LOT_AMOUNT = 5;
    String[] LOT_NAMES = {
            "Scythian vase", "Svyatoslav the brave sword", "Kyiv gold coin",
            "Golden Pectoral", "The cloak of Princess Olga"
    };
    int[] LOT_START_PRICE = {100, 200, 150, 400, 280};

    int MEMBER_MAX_MONEY = 1500;
    int MEMBER_MIN_MONEY = 400;
    int MIN_STEP = 20;

    //probability of having no wish to take step
    double PROBABILITY_SKIP_THE_STEP_INDEX = 0.9;
    //probability of taking step have no enough money (cheating)
    double PROBABILITY_CHEATING_INDEX = 0;

    int BANNED_SESSIONS = 3;
}
