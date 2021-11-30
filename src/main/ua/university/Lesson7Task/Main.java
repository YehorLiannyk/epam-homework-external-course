package main.ua.university.Lesson7Task;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        start();
    }

    static void start() {
        letsPLay();
    }

    static void letsPLay() {
        NumberController numberController = new NumberController(getNumber(), getNumberView());
        User[] users = numberController.getUsers();

        int mode = 0; // HARDCODE/ choosing game mode

        if (mode == 0)
            playWithTheSameNumber(users, numberController);
        else if ( mode == 1)
            playWithTheDiffNumber(users, numberController);

        numberController.printStatisticFromAllUsers(users, numberController);
    }

    static void playWithTheSameNumber(User[] users, NumberController numberController) {
        for (int i = 0; i < users.length; i++) {
            users[i] = numberController.createUser();
            playWithUser(users[i], numberController);
        }
    }

    static void playWithTheDiffNumber(User[] users, NumberController numberController) {
        for (int i = 0; i < users.length; i++) {
            users[i] = numberController.createUser();
            playWithUser(users[i], numberController);
            numberController = new NumberController(getNumber(), getNumberView());
        }
    }

    static User playWithUser(User user, NumberController numberController) {
        numberController.guessNumberFunc(user);
        return user;
    }

    static Number getNumber() {
        Number number = new Number();
        return number;
    }

    static NumberView getNumberView() {
        NumberView numberView = new NumberView();
        return numberView;
    }
}
