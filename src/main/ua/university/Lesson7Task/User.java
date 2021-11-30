package main.ua.university.Lesson7Task;

public class User {
    private String name;
    private int[][] statistic;
    public void setStatistic(int[][] statistic) {
        this.statistic = statistic;
    }

    public int[][] getStatistic() {
        return statistic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }
}
