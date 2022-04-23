package main.ua.advanced.practice5.task11_airport;

public class City {
    private final String name;
    private final int distance;

    public City(String name, int distance) {
        this.name = name;
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public String getName() {
        return name;
    }
}
