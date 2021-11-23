package main.ua.university.Lesson3Task;

public class Main {

    public static void main(String[] args) {
        int[][] matrix = {
                {31,32,33},
                {11,12,13},
                {1,2,3},
                {22,23,24}
        };
        printArrayElements(matrix);
        for (int j = 0; j < matrix.length; j++) {
            for (int i = 0; i < matrix.length - 1; i++) {
                int sum = sumOfElementsInArr(matrix[i]);
                int nextSum = sumOfElementsInArr(matrix[i + 1]);
                if (sum > nextSum) {
                    int[] temp = matrix[i];
                    matrix[i] = matrix[i + 1];
                    matrix[i + 1] = temp;
                }
            }
        }
        printArrayElements(matrix);
    }

    static int sumOfElementsInArr(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
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
