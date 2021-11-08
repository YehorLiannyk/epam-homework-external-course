package com.Task1;

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
        int number = 0;
        System.out.print("Input value (100 <= value <= 999): ");
        boolean check = true;
        Scanner scanner = new Scanner(System.in);
        while (check) {
            number = scanner.nextInt();
            scanner.nextLine();
            if (checkValue(number))
                check = false;
            else
                System.out.print("WARNING: Wrong value, 100 <= value <= 999. Try again: ");
        }
        return number;
    }

    boolean checkValue(int number) {
        return (number >= 100 && number <= 999);
    }

    void setNewValue() {
        int[] digits = getDigitsArrFromValue(value);
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

    int[] getDigitsArrFromValue(int number) {
        int[] digits = new int[3];
        for (int i = 0; i < digits.length; i++) {
            digits[i] = number % 10;
            number /= 10;
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
