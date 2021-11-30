package main.ua.university.Lesson7Task;

public class NumberView {

    public static final String TRY_AGAIN_MSG = "Try again: ";
    public static final String WRONG_NUMBER_MSG = "Unfortunately, it's a wrong number, try again, I'll help you";
    public static final String INPUT_AMOUNT_OF_PLAYERS = "Input amount of players: ";
    public static final String INPUT_INT = "Input number: ";
    public static final String INPUT_USERNAME = "Input name of user: ";
    public static final String STATISTIC = "==== STATISTIC ====";


    void inputValueFromMinToMax(int min, int max) {
        print("Input value from " + min + " to " + max + ": ");
    }

    void inputValueFromMinToMax(int[] range) {
        inputValueFromMinToMax(range[0], range[1]);
    }

    void tryToGuessMessage(int min, int max) {
        println("Try to guess my number");
        println("It's between " + min + " and " + max);
    }

    void printStatisticFromAllUsers(User[] users, NumberController numberController){
        println(STATISTIC);
        for (var user : users) {
            println("Statistic of User " + user.getName() + ": ");
            numberController.printStatistic(user);
        }
    }

    void printStatistic(int[][] statistic) {
        for (int i = 0; i < statistic.length; i++) {
            println("Step #" + i + ": " + "My number = " + statistic[i][0] + ", user's number = " + statistic[i][1]);
            println(", " + "range from " + statistic[i][2] + " to " + statistic[i][3]);
        }
    }

    void successMessage(int number) {
        println("How did you guess..? Okay, congrats, you're right, it was " + number);
    }

    public void printOwnError(String error) {
        println("WARNING: " + error + "!");
    }

    public static void print(String line) {
        System.out.print(line);
    }

    public static void println(String line) {
        System.out.println(line);
    }

}
