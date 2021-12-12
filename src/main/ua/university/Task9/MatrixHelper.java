package main.ua.university.Task9;

import java.util.Scanner;

public class MatrixHelper {
    Matrix mainMatrix;
    Matrix secondMatrix;

    public void start() {
        mainMatrix = createMatrix("mainMatrix");
        secondMatrix = createMatrix("secondMatrix");
        while(true)
            menu(mainMatrix, secondMatrix);
    }

    void menu(Matrix firstMatrix, Matrix secondMatrix) {
        int value = getMenuActionNumber();

        switch (value) {
            case 0: {
                System.out.println("Goodbye!");
                System.exit(0);
                break;
            }
            case 1: {
                printElementByIndex(firstMatrix);
                break;
            }
            case 2: {
                this.mainMatrix = setElementByIndex(firstMatrix);
                break;
            }
            case 3: {
                printElementByIndex(secondMatrix);
                break;
            }
            case 4: {
                this.secondMatrix = setElementByIndex(secondMatrix);
                break;
            }
            case 5: {
                tryAddMatrixToMainMatrix(firstMatrix, secondMatrix);
                tryDeductMatrixToMainMatrix(firstMatrix, secondMatrix);
                tryMultipleMatrixToMainMatrix(firstMatrix, secondMatrix);
                break;
            }
            case 6: {
                printMatrixInfo(firstMatrix);
                break;
            }
            case 7: {
                printMatrixInfo(secondMatrix);
                break;
            }
            default: break;
        }
    }

    void printElementByIndex(Matrix matrix) {
        System.out.println("Element with the index = " + getElementByIndex(matrix));
    }

    double getElementByIndex(Matrix matrix) {
        double value = 0;
        boolean check = true;
        while (check) {
            try {
                int row = getInt("Write index of row in matrix: ");
                int column = getInt("Write index of column in matrix: ");
                value = matrix.getElemByIndex(row, column);
                check = false;
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                System.out.println("Try again");
            }
        }
        return value;
    }

    Matrix setElementByIndex(Matrix matrix) {
        double value = 0;
        boolean check = true;
        while (check) {
            try {
                int row = getInt("Write index of row in matrix: ");
                int column = getInt("Write index of column in matrix: ");
                value = getDouble("Write new value");
                matrix.setElemByIndex(value, row, column);
                check = false;
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                System.out.println("Try again");
            }
        }
        return matrix;
    }

    int getMenuActionNumber() {
        final int start = 0;
        final int end = 7;
        System.out.println("Choose next action");
        System.out.println("0 - exit");
        System.out.println("1 - get element by index in mainMatrix");
        System.out.println("2 - set element by index in mainMatrix");
        System.out.println("3 - get element by index in secondMatrix");
        System.out.println("4 - set element by index in secondMatrix");
        System.out.println("5 - get result of adding, deducting and multiplication of main and second matrices");
        System.out.println("6 - get information about mainMatrix");
        System.out.println("7 - get information about secondMatrix");
        return getValueInRange(start, end);
    }

    private int getValueInRange(int start, int end) {
        int value = 0;
        boolean check = true;
        while (check) {
            value = getInt("Input number: ");
            if (value < start || value > end)
                System.out.println("Wrong number. Try again");
            else
                check = false;
        }
        return value;
    }


    void printMatrixInfo(Matrix matrix) {
        System.out.println("Matrix info: ");
        System.out.println("Matrix array:" + '\n' + matrix.toString());
        printRowsAndColumnAmountInMatrix(matrix);
    }

    void printRowsAndColumnAmountInMatrix(Matrix matrix) {
        System.out.println("Rows amount = " + matrix.getAmountOfRows() + ", columns amount = " + matrix.getAmountOfColumns());
    }

    boolean tryAddMatrixToMainMatrix(Matrix fMatrix, Matrix sMatrix) {
        try {
            fMatrix = fMatrix.addingTwoMatrices(fMatrix, sMatrix);
        } catch (MatrixException e) {
            System.err.println(e.getMessage());
            return false;
        }
        System.out.println("Result of adding secondMatrix to MainMatrix: ");
        System.out.println(fMatrix.toString());
        return true;
    }

    boolean tryMultipleMatrixToMainMatrix(Matrix fMatrix, Matrix sMatrix) {
        try {
            fMatrix = fMatrix.multipleTwoMatrices(fMatrix, sMatrix);
        } catch (MatrixException e) {
            System.err.println(e.getMessage());
            return false;
        }
        System.out.println("Result of multiplication secondMatrix to MainMatrix: ");
        System.out.println(fMatrix.toString());
        return true;
    }


    boolean tryDeductMatrixToMainMatrix(Matrix fMatrix, Matrix sMatrix) {
        try {
            fMatrix = fMatrix.deductingTwoMatrices(fMatrix, sMatrix);
        } catch (MatrixException e) {
            System.err.println(e.getMessage());
            return false;
        }
        System.out.println("Result of deducting secondMatrix to MainMatrix: ");
        System.out.println(fMatrix.toString());
        return true;
    }


    public Matrix createMatrix(String matrixName) {
        System.out.println("Let's create " + matrixName);
        int rows = getRowsAmount();
        int columns = getColumnsAmount();
        return new Matrix(createDoubleArr(rows, columns));
    }

    double[][] createDoubleArr(int rows, int columns) {
        double[][] mainArr = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                mainArr[i][j] = getDouble("Input value for element with index [" + i + "][" + j + "]: ");
            }
        }
        return mainArr;
    }

    int getRowsAmount() {
        return getInt("Input amount of rows in Matrix: ");
    }

    int getColumnsAmount() {
        return getInt("Input amount of columns in Matrix: ");
    }

    public static int getInt(String msg) {
        Scanner sc = new Scanner(System.in);
        System.out.print(msg);
        while(!sc.hasNextInt()) {
            System.err.print("Wrong value, input only digits: ");
            sc.next();
        }
        return sc.nextInt();
    }

    public static double getDouble(String msg) {
        Scanner sc = new Scanner(System.in);
        System.out.print(msg);
        while(!sc.hasNextDouble()) {
            System.err.print("Wrong value, input only real digits: ");
            sc.next();
        }
        return sc.nextDouble();
    }
}
