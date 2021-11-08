package com.Task2;

public class Main {

    public static void main(String[] args) {
        System.out.println("Task 1");
        callFirstTask();
        System.out.println("Task 2");
        callSecondTask();
        System.out.println("Task 3");
        callThirdTask();

    }

    static void callFirstTask() {
        FirstTask firstTask = new FirstTask();
        System.out.println("Your array before:");
        printArrayElements(firstTask.getNumbers());
        System.out.println("Your array after:");
        firstTask.setArrAfterProcessing();
        printArrayElements(firstTask.getNumbers());
    }

       static void callSecondTask() {
        SecondTask secondTask = new SecondTask();
           System.out.println("Your array:");
           printArrayElements(secondTask.getNumbers());
           System.out.println("Distance between max value = " + secondTask.getDistanceBetweenMaxValues());
    }

    static void callThirdTask() {
        ThirdTask thirdTask = new ThirdTask();
        System.out.println("Your array before:");
        printArrayElements(thirdTask.getNumbers());
        System.out.println("Your array after:");
        thirdTask.setArrAfterProcessing();
        printArrayElements(thirdTask.getNumbers());
    }

    static void printArrayElements(int[] arr) {
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

}
