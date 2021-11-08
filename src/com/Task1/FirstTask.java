package com.Task1;

import java.util.Scanner;

public class FirstTask {
    private int value;

    FirstTask(int value) {
        this.value = value;
    }

    FirstTask() {
        this.value = getValueFromInput();
    }

    private int getValueFromInput() {
        int digit = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input value: ");
        digit = scanner.nextInt();
        scanner.nextLine();
        return digit;
    }


    void setNewValue() {
        if (value > 0)
            value *= value;
        else if (value < 0)
            value *= -1;
    }

    int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }
}
