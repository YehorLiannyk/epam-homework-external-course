package com.company;

import java.util.Scanner;

public class ThirdTask {
    private int value;

    ThirdTask(int value) {
        this.value = value;
    }

    ThirdTask() {
        this.value = getValueFromInput();
    }

    private int getValueFromInput() {
        int number = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input value: ");
        number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }

    void setNewValue() {
        int[] digits = getDigitsArrFromValue(value);
        digits = sortIntArrByDecreasing(digits);
        value = sumOddNumbers(digits);
    }

    int sumOddNumbers(int[] digits) {
        int number = 0;
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] % 2 != 0) {
                number += digits[i];
            }
        }
        return number;
    }

    int getAmountOfDigitsInInt(int number) {
        int i = 0;
        boolean check = true;
        while(check) {
            if (number != 0) {
                number /= 10;
                i++;
            }
            else
                check = false;
        }
        return i;
    }

    int[] getDigitsArrFromValue(int number) {
        int[] digits = new int[getAmountOfDigitsInInt(number)];
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
