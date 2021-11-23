package main.ua.university.Task2;

import java.util.Scanner;

public class FirstTask {
    private int[] numbers;

    public FirstTask(int[] value) {
        this.numbers = value;
    }

    public FirstTask() {
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

    public void setArrAfterProcessing() {
        numbers = getArrAfterReversing(numbers);
    }

    int[] getArrAfterReversing(int[] arr) {
        int last = arr.length - 1;
        for (int i = 0; i < arr.length; i++) {
            if (last - i >= 1) {
                if (checkIfTheyAreEven(arr[i], arr[last])) {
                    int temp = arr[i];
                    arr[i] = arr[last];
                    arr[last] = temp;
                }
                last--;
            }
        }
        return arr;
    }

    boolean checkIfTheyAreEven(int fElem, int sElem) {
        return ((fElem % 2 == 0) && (sElem % 2 == 0));
    }

    public int[] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }
}
