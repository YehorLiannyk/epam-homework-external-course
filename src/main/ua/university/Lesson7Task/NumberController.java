package main.ua.university.Lesson7Task;

import java.util.Scanner;

public class NumberController {
    private Number number;
    private NumberView numberView;
    private int[][] statistic;

    public NumberController(Number number, NumberView numberView) {
        this.number = number;
        this.numberView = numberView;
    }

    public int[][] getStatistic() {
        return statistic;
    }

    public void inputValueFromMinToMax(int min, int max) {
        numberView.inputValueFromMinToMax(min, max);
    }

    public void inputValueFromMinToMax(int[] range) {
        inputValueFromMinToMax(range[0], range[1]);
    }

    public void tryToGuessText(int min, int max) {
        numberView.tryToGuessText(min, max);
    }

    public void tryToGuessText(int[] numbers) {
        tryToGuessText(numbers[0], numbers[1]);
    }

    public int getNumber() {
        return number.getNumber();
    }

    public void setNumber(int number) {
        this.number.setNumber(number);
    }

    public void setStatistic(int[][] statistic) {
        this.statistic = statistic;
    }

    public int[] getNumberRange(int realNumber, int userNumber, int[] range) {
        int min = range[0];
        int max = range[1];

        if (userNumber > realNumber) {
            max = userNumber;
        }
        else if (userNumber < realNumber) {
            min = userNumber;
        }
        else {
            min = realNumber;
            max = realNumber;
        }
        return new int[] {min, max};
    }

    public int getMin() {
        return number.getMin();
    }

    public int getMax() {
        return number.getMax();
    }

    public void printStatistic() {
        numberView.printStatistic(statistic);
    }

    public int[][] addStatistic(int realNumber, int userNumber, int[] range) {
        int[][] arr;
        if (statistic == null) {
            statistic = new int[1][];
            arr = new int[statistic.length][];
        }
        else
            arr = new int[statistic.length + 1][];

        System.arraycopy(statistic, 0, arr, 0, statistic.length);
        arr[findFreeIndex(arr)] = new int[] {realNumber, userNumber, range[0], range[1]};
        return arr;
    }

    public int findFreeIndex(int[][] arr) {
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                index = i;
                break;
            }
        }
        return index;
    }

    public boolean checkingRangeEquality( int[] range) {
        boolean check = false;
            if (range[0] != range[1]) {
                numberView.wrongNumber();
            }
            else {
                numberView.successText(range[0]);
                check = true;
            }
        return check;
    }


}
