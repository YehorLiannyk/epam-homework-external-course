package main.ua.university.Lesson7Task;

import java.util.Random;
import java.util.Scanner;


public class Number {
    private int number;

    private final int min = 0;
    private final int max = 100;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Number() {
        Random random = new Random();
        number = random.nextInt((max) + min);
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

}
