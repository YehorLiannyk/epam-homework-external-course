package com.Task4;

import java.util.Scanner;

public class ThirdTask {
    static int[] formArithmeticProgression(int a, int t, int n) {
        int[] arr = new int[n];
        arr[0] = a;
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i - 1] + t;
        }
        return arr;
    }

    public static int multiArithmeticElements(int[] arr) {
        int multi = 1;
        for (int j : arr) {
            multi *= j;
        }
        return multi;
    }

    public static int[] createArithmeticProgression() {
        int a = inputNeededElem("a");
        int t = inputNeededElem("t");
        int n = inputNeededElem("n");
        return formArithmeticProgression(a, t, n);

    }
    public static int inputNeededElem(String element) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input value for "+ element + ": ");
        int n = sc.nextInt();
        sc.nextLine();
        return n;
    }
}
