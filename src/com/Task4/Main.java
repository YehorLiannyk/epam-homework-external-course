package com.Task4;

import java.util.Scanner;

public class Main {

    public enum SortOrder {
        ASCENDING {
            public int[] getSorted(int[] arr) {
                int[] numbers = arr.clone();
                for (int i = 0; i < numbers.length; i++)
                    for (int j = 0; j < numbers.length - 1; j++)
                        if (numbers[j] > numbers[j + 1]) {
                            int temp = numbers[j];
                            numbers[j] = numbers[j + 1];
                            numbers[j + 1] = temp;
                        }
                return numbers;
            }
        },
        DESCENDING {
            public int[] getSorted(int[] arr) {
                int[] numbers = arr.clone();
                for (int i = 0; i < numbers.length; i++)
                    for (int j = 0; j < numbers.length - 1; j++)
                        if (numbers[j] < numbers[j + 1]) {
                            int temp = numbers[j];
                            numbers[j] = numbers[j + 1];
                            numbers[j + 1] = temp;
                        }
                return numbers;
            }
        };
        public abstract int[] getSorted(int[] arr);
    }

    public static void main(String[] args) {
        SortOrder sortOrder = SortOrder.ASCENDING;

        //First
        int[] firstArr = getValueArrayFromInput();
        printArrayElements(firstArr);
        System.out.println("FIRST TASK");
        FirstTask.printIsSorted(firstArr, sortOrder);

        //Second
        System.out.println("SECOND TASK");
        firstArr = SecondTask.transform(firstArr,sortOrder);
        System.out.print("Your array after transforming: ");
        printArrayElements(firstArr);

        //Third
        System.out.println("THIRD TASK");
        int[] secondArr = ThirdTask.createArithmeticProgression();
        printArrayElements(secondArr);
        System.out.println(ThirdTask.multiArithmeticElements(secondArr));
        //Fourth
        System.out.println("FOURTH TASK");
        double[] thirdArr = FourthTask.createGeometricProgression();
        printArrayElements(thirdArr);
        System.out.println(FourthTask.sumGeometricElements(thirdArr));
    }

    static void FTask() {

    }

    static void printArrayElements(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }

    static void printArrayElements(double[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }

    static void printArrayElements(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int[] getValueArrayFromInput() {
        int amount = inputAmountOfElementsInArr();
        int[] digit = inputElementsInArr(amount);
        return digit;
    }

    static int inputAmountOfElementsInArr() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input amount of elements in array: ");
        int amount = sc.nextInt();
        sc.nextLine();
        return amount;
    }

    static int[] inputElementsInArr(int amount) {
        int[] arr = new int[amount];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < amount; i++) {
            System.out.print("Input element #" + i  + ": ");
            arr[i] = sc.nextInt();
            sc.nextLine();
        }
        return arr;
    }
}
