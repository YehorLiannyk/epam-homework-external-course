package main.ua.university.finalTask.pl;

import main.ua.university.finalTask.bll.IDHelper;

import java.util.Scanner;

public class GettingValuesFromInput {

    public static int getValueInRange(int start, int end) {
        int value = 0;
        boolean check = true;
        while (check) {
            value = getInt(StringConst.INPUT_NUMBER);
            if (value < start || value > end)
                System.out.println(StringConst.WRONG_NUMBER_TRY_AGAIN);
            else
                check = false;
        }
        return value;
    }

    public static int getInt(String msg) {
        Scanner sc = new Scanner(System.in);
        System.out.print(msg);
        while (!sc.hasNextInt()) {
            System.err.print(StringConst.WRONG_VALUE_INPUT_ONLY_DIGITS);
            sc.next();
        }
        return sc.nextInt();
    }

    public static double getDouble(String msg) {
        Scanner sc = new Scanner(System.in);
        System.out.print(msg);
        while (!sc.hasNextDouble()) {
            System.err.print(StringConst.WRONG_VALUE_INPUT_ONLY_REAL_DIGITS);
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
                System.err.print(StringConst.WRONG_VALUE_INPUT_ONLY_REAL_DIGITS);
            } catch (IllegalArgumentException e) {
                System.err.print(StringConst.WRONG_VALUE_USE_THE_RIGHT_FORMAT_FOR_ID);
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
            System.err.print(StringConst.WRONG_VALUE_USE_ONLY_TRUE_OR_FALSE);
            sc.next();
        }
        return sc.nextBoolean();
    }

}
