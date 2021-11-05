package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println("Task 1");
        callFirstTask();
        System.out.println("Task 2");
        callSecondTask();
        System.out.println("Task 3");
        callThirdTask();
        System.out.println("Task 4");
        callFourthTask();

    }

    static void callFirstTask() {
        FirstTask firstTask = new FirstTask();
        System.out.println("Your value: " + firstTask.getValue());
        firstTask.setNewValue();
        System.out.println("Your value after: " + firstTask.getValue());
    }

    static void callSecondTask() {
        SecondTask secondTask = new SecondTask();
        System.out.println("Your value: " + secondTask.getValue());
        secondTask.setNewValue();
        System.out.println("Your value after: " + secondTask.getValue());
    }

    static void callThirdTask() {
        ThirdTask thirdTask = new ThirdTask();
        System.out.println("Your value: " + thirdTask.getValue());
        thirdTask.setNewValue();
        System.out.println("Your value after: " + thirdTask.getValue());
    }

    static void callFourthTask() {
        FourTask fourTask = new FourTask();
        System.out.println("Your value: " + fourTask.getValue());
        System.out.println("Amount of binary one in value: " + fourTask.getAmountOfBinaryOne());
    }
}
