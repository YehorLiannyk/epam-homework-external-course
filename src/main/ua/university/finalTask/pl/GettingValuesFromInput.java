package main.ua.university.finalTask.pl;

import main.ua.university.finalTask.bll.IDHelper;

import java.util.Scanner;

public class GettingValuesFromInput {
    public static int getValueInRange(int start, int end) {
        int value = 0;
        boolean check = true;
        while (check) {
            value = getInt("Input number: ");
            if (value < start || value > end)
                System.out.println("Wrong number. Try again");
            else
                check = false;
        }
        return value;
    }

    public  static int getInt(String msg) {
        Scanner sc = new Scanner(System.in);
        System.out.print(msg);
        while(!sc.hasNextInt()) {
            System.err.print("Wrong value, input only digits: ");
            sc.next();
        }
        return sc.nextInt();
    }

    public static double getDouble(String msg) {
        Scanner sc = new Scanner(System.in);
        System.out.print(msg);
        while(!sc.hasNextDouble()) {
            System.err.print("Wrong value, input only real digits: ");
            sc.next();
        }
        return sc.nextDouble();
    }

    public static String getString(String msg) {
        Scanner sc = new Scanner(System.in);
        System.out.print(msg);
        return sc.nextLine();
    }

    public static int getObjectId(String msg) {
        Scanner sc = new Scanner(System.in);
        System.out.print(msg);
        while(!sc.hasNextInt() || !isThisId(sc.nextInt())) {
            System.err.print("Wrong value, input only real digits and use the right format \'XXXXXX\': ");
            sc.next();
        }
        return sc.nextInt();
    }

    private static boolean isThisId(int id) {
        if (id <= IDHelper.getEndIDNumber() && id >= IDHelper.getStartIDNumber())
            return true;
        else
            return false;
    }

}
