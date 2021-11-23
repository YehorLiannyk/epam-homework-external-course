package main.ua.university.Task4;

import static main.ua.university.Task4.FirstTask.isSorted;

public class SecondTask {
    public static int[] transform(int[] arr, Main.SortOrder sortOrder) {
        if (isSorted(arr, sortOrder)) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] += i;
            }
        }
        return arr;
    }
}
