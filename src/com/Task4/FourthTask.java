package com.Task4;

import java.util.Scanner;

public class FourthTask {
    static double[] formGeometricProgression(double a, double t, int n) {
        double[] arr = new double[n];
        arr[0] = a;
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i - 1] * t;
        }
        return arr;
    }

    public static double sumGeometricElements(double[] arr) {
        double sum = 0;
        for (double j : arr) {
            sum += j;
        }
        return sum;
    }

    public static double[] createGeometricProgression() {
        double a = ThirdTask.inputNeededElem("a");
        double t = getT();
        double alim = ThirdTask.inputNeededElem("alim");
        int n = getNWithLoop(a, t, alim);
        return formGeometricProgression(a, t, n);
    }

    static double getT() {
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        double t = 0;
        System.out.print("Input value for t (0 < t < 1): ");
        while (check) {
            t = sc.nextDouble();
            sc.nextLine();
            if (!checkT(t)) {
                System.out.println("WARNING: Wrong value. (0 < t < 1)");
                System.out.print("Try again: ");
            }
            else
                check = false;
        }
        return t;
    }


    static int getNWithLoop(double a, double t, double alim) {
        int n = 1;
        while (a * t > alim) {
            n++;
            a *= t;
        }
        return n;
    }

    static boolean checkT(double t) {
        return (t <= 0 || t >= 1) ? false : true;
    }
}
