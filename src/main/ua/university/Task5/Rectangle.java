package main.ua.university.Task5;

public class Rectangle {
    private double sideA;
    private double sideB;

    public Rectangle(double sideA, double sideB) {
        this.sideA = sideA;
        this.sideB = sideB;
    }

    public Rectangle(double sideA) {
        this(sideA, 5);
    }

    public Rectangle() {
        this (4, 3);
    }

    public double getSideA() {
        return sideA;
    }

    public void setSideA(double sideA) {
        this.sideA = sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public void setSideB(double sideB) {
        this.sideB = sideB;
    }

    public double getPerimeter() {
        return 2 * (sideA + sideB);
    }

    public double getArea() {
        return sideA * sideB;
    }

    public void replaceSides() {
        double temp = sideA;
        sideA = sideB;
        sideB = temp;
    }

    public boolean isSquare() {
        return (sideA == sideB);
    }
}
