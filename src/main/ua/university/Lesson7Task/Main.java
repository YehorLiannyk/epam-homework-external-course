package main.ua.university.Lesson7Task;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        start();
    }

    static void start() {
        NumberController numberController = new NumberController(getNumber(), getNumberView());
        guessNumberFunc(numberController);
    }

    static Number getNumber() {
        Number number = new Number();
        return number;
    }

    static NumberView getNumberView() {
        NumberView numberView = new NumberView();
        return numberView;
    }

    static void guessNumberCycle(NumberController numberController, int[] range) {
        boolean check = true;
        while (check) {
            numberController.tryToGuessText(range);
            int userNumber = getNumberFromInput(numberController, range);
            int realNumber = numberController.getNumber();
            range = numberController.getNumberRange(realNumber, userNumber, range);
            check = numberController.checkingRangeEquality(numberController, range);
            numberController.setStatistic(numberController.addStatistic(realNumber,userNumber, range));
        }
    }

    static void guessNumberFunc(NumberController numberController) {
        int[] range = {numberController.getMin(), numberController.getMax()};
        guessNumberCycle(numberController, range);
        numberController.printStatistic();
    }

    static int inputNumber() {
        int number = -1;
        boolean check = true;
        while (check) {
            try {
                Scanner sc = new Scanner(System.in);
                number = sc.nextInt();
                sc.nextLine();
                check = false;
            } catch (InputMismatchException e) {
                printOwnError("Use only digits");
                printTryAgain();
            }
        }
        return number;
    }

    static int getNumberFromInput(NumberController controller, int[] range) {
        controller.inputValueFromMinToMax(range);
        int number = -1;
        boolean check = true;
        while (check) {
            try {
                number = inputNumber();
                if (number < range[0] || number > range[1]) {
                    throw new IllegalArgumentException();
                }
                check = false;
            } catch (IllegalArgumentException e) {
                printOwnError("Your number is out of range");
                printTryAgain();
            }
        }
        return number;
    }

    static void printOwnError(String error) {
        System.out.println("WARNING: " + error + "!");
    }

    static void printTryAgain() {
        System.out.print("Try again: ");
    }

    static void print(String line) {
        System.out.print(line);
    }
}
