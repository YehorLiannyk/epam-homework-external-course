package main.ua.university.Lesson7Task;

import main.ua.university.Lesson7Task.Number.Number;
import main.ua.university.Lesson7Task.Number.NumberController;
import main.ua.university.Lesson7Task.Number.NumberView;
import main.ua.university.Lesson7Task.Number.User;

public class GameNumber {
    public void letsPLay() {
        NumberController numberController = new NumberController(getNumber(), getNumberView());
        User[] users = numberController.getUsers();

        int mode = 1; // HARDCODE/ choosing game mode

        if (mode == 0)
            playWithTheSameNumber(users, numberController);
        else if ( mode == 1)
            playWithTheDiffNumber(users, numberController);

        numberController.printStatisticFromAllUsers(users, numberController);
    }

    void playWithTheSameNumber(User[] users, NumberController numberController) {
        for (int i = 0; i < users.length; i++) {
            users[i] = numberController.createUser();
            numberController.playWithUser(users[i], numberController);
        }
    }

    void playWithTheDiffNumber(User[] users, NumberController numberController) {
        for (int i = 0; i < users.length; i++) {
            users[i] = numberController.createUser();
            numberController.playWithUser(users[i], numberController);
            numberController = new NumberController(getNumber(), getNumberView());
        }
    }

    Number getNumber() {
        Number number = new Number();
        return number;
    }

    NumberView getNumberView() {
        NumberView numberView = new NumberView();
        return numberView;
    }
}
