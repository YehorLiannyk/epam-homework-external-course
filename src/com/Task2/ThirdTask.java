package com.Task2;

public class ThirdTask {
    private int[][] numbers = new int[][] {
            {2, 4, 3, 3},
            {5, 7, 8, 5},
            {2, 4, 3, 3},
            {5, 7, 8, 5}
    };

    public ThirdTask(int[][] value) {
        this.numbers = value;
    }

    public ThirdTask() {}

    public void setArrAfterProcessing() {
        numbers = arrAfterProcessing(numbers);
    }

    int[][] arrAfterProcessing(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (i - j >= 1)
                    arr[i][j] = 0;
                else if (i - j < 0)
                    arr[i][j] = 1;
            }
        }
        return arr;
    }

    public int[][] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[][] numbers) {
        this.numbers = numbers;
    }
}
