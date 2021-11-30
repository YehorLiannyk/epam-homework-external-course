package main.ua.university.Lesson7Task;

import java.util.InputMismatchException;
import java.util.Scanner;

public class NumberController {
    private Number number;
    private NumberView numberView;
    private User user;


    public NumberController(Number number, NumberView numberView) {
        this.number = number;
        this.numberView = numberView;
    }

    void tryToGuessText(int[] numbers) {
        numberView.tryToGuessMessage(numbers[0], numbers[1]);
    }

    public int getNumber() {
        return number.getNumber();
    }

    public void setNumber(int number) {
        this.number.setNumber(number);
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

    public void printStatistic(User user) {
        numberView.printStatistic(user.getStatistic());
    }

    public int[][] addStatistic(int[][] statistic, int realNumber, int userNumber, int[] range) {
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
                NumberView.println(NumberView.WRONG_NUMBER_MSG);
            }
            else {
                numberView.successMessage(range[0]);
                check = true;
            }
        return check;
    }

    public void guessNumberCycle(int[] range) {
        boolean check = false;
        while (!check) {
            tryToGuessText(range);
            int userNumber = getNumberFromInputWithinRange(range);
            int realNumber = getNumber();
            range = getNumberRange(realNumber, userNumber, range);
            check = checkingRangeEquality(range);
            user.setStatistic(addStatistic(user.getStatistic(), realNumber, userNumber, range));
        }
    }

    public void guessNumberFunc(User user) {
        this.user = user;
        int[] range = {number.getMin(), number.getMax()};
        guessNumberCycle(range);
        //printStatistic(user); // print general statistic in the end
    }

    int inputNumber() {
        int number = -1;
        boolean check = true;
        while (check) {
            try {
                Scanner sc = new Scanner(System.in);
                number = sc.nextInt();
                sc.nextLine();
                check = false;
            } catch (InputMismatchException e) {
                numberView.printOwnError("Use only digits");
                NumberView.print(NumberView.TRY_AGAIN_MSG);
            }
        }
        return number;
    }

    public int getNumberFromInputWithinRange(int[] range) {
        numberView.inputValueFromMinToMax(range);
        int number = -1;
        boolean check = true;
        while (check) {
            try {
                number = inputNumber();
                if (number < range[0] || number > range[1]) {
                    throw new IllegalArgumentException();
                }
                check = false;
            }
            catch (IllegalArgumentException e) {
                numberView.printOwnError("Your number is out of range");
                NumberView.print(NumberView.TRY_AGAIN_MSG);
            }
            catch (InputMismatchException e) {
                numberView.printOwnError("It isn't a number");
                NumberView.print(NumberView.TRY_AGAIN_MSG);
            }
        }
        return number;
    }

    public int getNumberFromInput() {
        int number = -1;
        boolean check = true;
        while (check) {
            try {
                number = inputNumber();
                check = false;
            }
            catch (InputMismatchException e) {
                numberView.printOwnError("Use only digits");
                NumberView.print(NumberView.TRY_AGAIN_MSG);
            }
        }
        return number;
    }

    public String getStringFromInput() {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        return line;
    }

    int getAmountOfPlayers() {
        NumberView.print(NumberView.INPUT_AMOUNT_OF_PLAYERS);
        return getNumberFromInput();
    }

    public User[] getUsers() {
        int amount = getAmountOfPlayers();
        User[] users = new User[amount];
        return users;
    }

    public User createUser() {
        NumberView.print(NumberView.INPUT_USERNAME);
        return new User(getStringFromInput());
    }

    public void printStatisticFromAllUsers(User[] users, NumberController numberController) {
        numberView.printStatisticFromAllUsers(users, numberController);
    }



}
