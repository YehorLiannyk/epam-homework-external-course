package main.ua.advanced.practice5.task11_airport;

public interface AirportDefaults {
    City Chernihiv = new City("Chernihiv", 128);
    City Dnipro = new City("Dnipro", 398);
    City Kharkiv = new City("Kharkiv", 410);
    City Lviv = new City("Lviv", 470);
    City Simferopol = new City("Simferopol", 670);
    City mainCity = new City("Kyiv", 0);
    City[] cities = new City[]{Chernihiv, Dnipro, Kharkiv, Lviv, Simferopol};


    int AIRCRAFT_AMOUNT = 3;
    int MIN_AIRCRAFT_CAPACITY = 85;
    int MAX_AIRCRAFT_CAPACITY = 210;

    int TERMINAL_AMOUNT = 3;
    int TERMINAL_MILLISECONDS_ONE_PASSENGER = 80;

    int GANGWAY_AMOUNT = 5;
    int GANGWAY_MILLISECONDS_ONE_PASSENGER = 100;

    int RELEASING_TIME = 2000;

    String WELCOME_MSG = "Dear passengers, thank you for using our airlines. Welcome to " + mainCity.getName();
    String GOODBYE_MSG = "Dear passengers, have a good fly. You're going from " + mainCity.getName() + " to ";

}

