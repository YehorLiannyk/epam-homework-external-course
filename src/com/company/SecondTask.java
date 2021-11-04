package com.company;

import java.util.Scanner;

public class SecondTask {
    private int value;

    SecondTask(int value) {

        if (checkValue(value))
            this.value = value;
        else
            value = 0;
    }

    SecondTask() {
        this.value = getValueFromInput();
    }

    private int getValueFromInput() {
        int value = 0;
        System.out.print("Input value (100 <= value <= 999): ");
        boolean check = true;
        while (check) {
            Scanner scanner = new Scanner(System.in);
            value = scanner.nextInt();
            scanner.nextLine();
            //if (scanner.hasNext())
            if (checkValue(value))
                check = false;
            else
                System.out.print("WARNING: Wrong value, 100 <= value <= 999. Try again: ");
            scanner.close();
        }
        return value;
    }

    boolean checkValue(int value) {
        return (value >= 100 && value <= 999);
    }

    void setNewValue() {
        int[] digits = getDigitsArrFromValue();
        digits = sortIntArrByDecreasing(digits);
        value = fromDigitsArrToNumber(digits);
    }

    int fromDigitsArrToNumber(int[] digits) {
        int number = 0;
        for (int i = 0; i < digits.length; i++) {
            number = (number * 10) + digits[i];
        }
        return number;
    }

    int[] getDigitsArrFromValue() {
        int[] digits = new int[3];
        for (int i = 0; i < digits.length; i++) {
            digits[i] = value % 10;
            value /= 10;
        }
        return digits;
    }

    int[] sortIntArrByDecreasing(int[] arr) {
        for (int i = 0; i + 1 < arr.length; i++) {
            for (int j = 0; j + 1 < arr.length - i; j++) {
                if (arr[j + 1] > arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }


    int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }
}
