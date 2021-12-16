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

    public static int getInt(String msg) {
        Scanner sc = new Scanner(System.in);
        System.out.print(msg);
        while (!sc.hasNextInt()) {
            System.err.print("Wrong value, input only digits: ");
            sc.next();
        }
        return sc.nextInt();
    }

    public static double getDouble(String msg) {
        Scanner sc = new Scanner(System.in);
        System.out.print(msg);
        while (!sc.hasNextDouble()) {
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
        int number = 0;
        boolean check = true;
        while (check) {
            try {
                String text = sc.nextLine();
                number = Integer.parseInt(text);
                if (isThisId(number)) check = false;
            } catch (NumberFormatException e) {
                System.err.print("Wrong value, input only real digits: ");
            } catch (IllegalArgumentException e) {
                System.err.print("Wrong value, use the right format 'XXXXXX': ");
            }
        }
        return number;
    }


    private static boolean isThisId(int id) {
        if (id <= IDHelper.getEndIDNumber() && id >= IDHelper.getStartIDNumber())
            return true;
        else {
            throw new IllegalArgumentException();
        }
    }

    public static boolean getBoolean(String msg) {
        Scanner sc = new Scanner(System.in);
        System.out.print(msg);
        while (!sc.hasNextBoolean()) {
            System.err.print("Wrong value, : ");
            sc.next();
        }
        return sc.nextBoolean();
    }

}
