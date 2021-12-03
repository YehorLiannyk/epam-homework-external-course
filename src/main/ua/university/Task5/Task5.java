package main.ua.university.Task5;

import java.security.Signature;
import java.util.Scanner;

public class Task5 {
    public static void main(String[] args) {
        arrRectangleFunc();
    }

    static void arrRectangleFunc() {
        System.out.print("Input amount of element in array: ");
        int amount = inputInt();

        ArrayRectangle rectangles = new ArrayRectangle(amount);
        rectangles = getRectangles(amount);

        printArrRectangleInfo(rectangles);
    }

    static void printArrRectangleInfo(ArrayRectangle rectangles) {
        Rectangle[] arr = rectangles.getArray_rectangle();
        for (int i = 0; i < arr.length; i++) {
            System.out.println("Side A: " + arr[i].getSideA() + ", " + "side B: " + arr[i].getSideB());
        }
        System.out.println("numberMaxArea = " + rectangles.numberMaxArea());
        System.out.println("numberMinPerimeter = " + rectangles.numberMinPerimeter());
        System.out.println("numberSquare = " + rectangles.numberSquare());
    }

    static int inputInt() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        return n;
    }

    static double inputDouble() {
        Scanner sc = new Scanner(System.in);
        double n = sc.nextDouble();
        sc.nextLine();
        return n;
    }

    static double getSide(String sideName) {
        System.out.print("Input value for side " + sideName + ": ");
        return inputDouble();
    }

    static ArrayRectangle getRectangles(int amount) {
        ArrayRectangle rectangles = new ArrayRectangle(amount);
        for (int i = 0; i < amount; i++) {
            Rectangle rect = new Rectangle(getSide("A"), getSide("B"));
            rectangles.addRectangle(rect);
        }
        return rectangles;
    }
}