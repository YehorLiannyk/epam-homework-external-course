package main.ua.university.Lesson7Task.Number;

import java.util.Random;
import java.util.Scanner;


public class Number {
    private int number;

    private int min = 0;
    private int max = 100;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Number() {
        number = getRandomNumber();
    }

    public int getRandomNumber() {
        Random random = new Random();
        number = random.nextInt((max) + min);
        return number;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

}
