package main.ua.university.Lesson7Task;

import java.util.Scanner;

public class NumberView {

    void inputValueFromMinToMax(int min, int max) {
        System.out.print("Input value from " + min + " to " + max + ": ");
    }

    void tryToGuessText(int min, int max) {
        System.out.println("Try to guess my number");
        System.out.println("It's between " + min + " and " + max);
    }


    void printStatistic(int[][] statistic) {
        for (int i = 0; i < statistic.length; i++) {
            System.out.print("Step #" + i + ": " + "My number = " + statistic[i][0] + ", your number = " + statistic[i][1]);
            System.out.println(", " + "range from " + statistic[i][2] + " to " + statistic[i][3]);
        }
    }

    void wrongNumber() {
        System.out.println("Unfortunately, it's a wrong number, try again, I'll help you");
    }

    void successText(int number) {
        System.out.println("How did you guess..? Okay, congrats, you're right, it was " + number);
    }

}
