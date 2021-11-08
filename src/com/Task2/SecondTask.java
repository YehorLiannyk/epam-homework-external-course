package com.Task2;

import java.util.Scanner;

public class SecondTask {
    private int[] numbers;

    public SecondTask(int[] value) {
        this.numbers = value;
    }

    public SecondTask() {
        this.numbers = getValueArrayFromInput();
    }

    private int[] getValueArrayFromInput() {
        int amount = inputAmountOfElementsInArr();
        int[] digit = inputElementsInArr(amount);
        return digit;
    }

    int inputAmountOfElementsInArr() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input amount of elements in array: ");
        int amount = sc.nextInt();
        sc.nextLine();
        return amount;
    }

    int[] inputElementsInArr(int amount) {
        int[] arr = new int[amount];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < amount; i++) {
            System.out.print("Input element #" + i  + ": ");
            arr[i] = sc.nextInt();
            sc.nextLine();
        }
        return arr;
    }

    public int getDistanceBetweenMaxValues() {
        int max = findMaxValue(numbers);
        int distance = findDistanceBetweenMaxValue(numbers, max);
        return distance;
    }

    int findDistanceBetweenMaxValue(int[] arr, int max) {
        int dist = 0;
        boolean entry = false;
        int firstInd = 0;
        int lastInd = 0;
        for (int i = 0; i < arr.length; i++) {
            if (max == arr[i] && !entry) {
                firstInd = i;
                entry = true;
                continue;
            }
            if (max == arr[i] && entry)
                lastInd = i;
        }
        dist = lastInd == 0 ? 0 :lastInd - firstInd;
        return  dist;
    }

    int findMaxValue(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i])
                max = arr[i];
        }
        return max;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }
}
