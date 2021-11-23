package main.ua.university.Task4;

public class FirstTask {
    public static boolean isSorted(int[] arr, Main.SortOrder sortOrder) {
        int[] compArr = sortOrder.getSorted(arr);
        boolean check = false;
        if (arrayComparer(arr, compArr))
            check = true;
        return check;
    }

    public static void printIsSorted(int[] arr, Main.SortOrder sortOrder) {
        if (isSorted(arr, sortOrder)) {
            System.out.println("Your array is " + sortOrder.name());
        }
        else {
            System.out.println("Your array isn`t " + sortOrder.name());
        }
    }

    static boolean arrayComparer(int[] arr, int[] compArr) {
        boolean theSame = true;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != compArr[i]) {
                theSame = false;
            }
        }
        return theSame;
    }
}
