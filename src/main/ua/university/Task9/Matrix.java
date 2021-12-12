package main.ua.university.Task9;

import java.util.Arrays;

public class Matrix {
    private double[][] matrix;

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public Matrix(int rowsAmount, int columnsAmount) {
        matrix = new double[rowsAmount][columnsAmount];
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public double getElemByIndex(int row, int column) {
        if (isReceivedRowsAndColumnsRight(row, column))
            return matrix[row][column];
        return 0;
    }


    public boolean setElemByIndex(double value, int row, int column) {
        if (isReceivedRowsAndColumnsRight(row, column)) {
            matrix[row][column] = value;
            return true;
        } else
            return false;
    }

    private boolean isReceivedRowsAndColumnsRight(int rowIndex, int columnIndex) {
        boolean check = false;
        if (rowIndex > getAmountOfRows() || rowIndex < 0)
            throw new IndexOutOfBoundsException("The received row is bigger than amount of rows in the Matrix or less 0");
        else if (columnIndex > getAmountOfColumns() || columnIndex < 0)
            throw new IndexOutOfBoundsException("The received column is bigger than amount of columns in the Matrix or less 0");
        else
            check = true;
        return check;
    }

    public boolean addAnotherMatrix(Matrix anotherMatrix) {
        if (isReceivedMatrixCanBeAdd(anotherMatrix)) {
            matrix = addingTwoMatrices(matrix, anotherMatrix.getMatrix());
            return true;
        } else
            return false;
    }

    private double[][] addingTwoMatrices(double[][] fMatrix, double[][] sMatrix) {
        double[][] newMatrix = new double[fMatrix.length][fMatrix[0].length];
        for (int i = 0; i < newMatrix.length; i++) {
            for (int j = 0; j < newMatrix[0].length; j++) {
                newMatrix[i][j] = fMatrix[i][j] + sMatrix[i][j];
            }
        }
        return newMatrix;
    }

    public Matrix addingTwoMatrices(Matrix fMatrix, Matrix sMatrix) {
        if (isReceivedMatrixCanBeAdd(fMatrix, sMatrix))
            return new Matrix(addingTwoMatrices(fMatrix.getMatrix(), sMatrix.getMatrix()));
        return null;
    }

    public boolean deductAnotherMatrix(Matrix anotherMatrix) {
        if (isReceivedMatrixCanBeAdd(anotherMatrix)) {
            matrix = deductingTwoMatrices(matrix, anotherMatrix.getMatrix());
            return true;
        } else
            return false;
    }

    private double[][] deductingTwoMatrices(double[][] fMatrix, double[][] sMatrix) {
        double[][] newMatrix = new double[fMatrix.length][fMatrix[0].length];
        for (int i = 0; i < newMatrix.length; i++) {
            for (int j = 0; j < newMatrix[0].length; j++) {
                newMatrix[i][j] = fMatrix[i][j] - sMatrix[i][j];
            }
        }
        return newMatrix;
    }

    public Matrix deductingTwoMatrices(Matrix fMatrix, Matrix sMatrix) {
        if (isReceivedMatrixCanBeAdd(fMatrix, sMatrix))
            return new Matrix(deductingTwoMatrices(fMatrix.getMatrix(), sMatrix.getMatrix()));
        return null;
    }

    public boolean multiAnotherMatrix(Matrix anotherMatrix) {
        if (isReceivedMatrixCanBeMult(anotherMatrix)) {
            matrix = multipleTwoMatrices(matrix, anotherMatrix.getMatrix());
            return true;
        } else
            return false;
    }

    public double[][] multipleTwoMatrices(double[][] fMatrix, double[][] sMatrix) {
        double[][] newMatrix = new double[fMatrix.length][sMatrix[0].length];

        for (int i = 0; i < newMatrix.length; i++) {
            for (int j = 0; j < newMatrix[0].length; j++) {
                double sum = 0;
                for (int k = 0; k < newMatrix[0].length; k++)
                    sum += fMatrix[i][k] * sMatrix[k][j];
                newMatrix[i][j] = sum;
            }
        }
        return newMatrix;
    }

    public Matrix multipleTwoMatrices(Matrix fMatrix, Matrix sMatrix) {
        if (isReceivedMatrixCanBeMult(fMatrix, sMatrix)) {
            return new Matrix(multipleTwoMatrices(fMatrix.getMatrix(), sMatrix.getMatrix()));
        }
        return null;
    }

    public boolean isReceivedMatrixCanBeAdd(Matrix mainMatrix, Matrix anotherMatrix) {
        boolean check = false;
        if (anotherMatrix.getAmountOfRows() != mainMatrix.getAmountOfRows())
            throw new MatrixException("Rows amount isn't equal");
        else if (anotherMatrix.getAmountOfColumns() != mainMatrix.getAmountOfColumns())
            throw new MatrixException("Columns amount isn't equal");
        else
            check = true;
        return check;
    }

    public boolean isReceivedMatrixCanBeAdd(Matrix anotherMatrix) {
        return  isReceivedMatrixCanBeAdd(this, anotherMatrix);
    }

    public boolean isReceivedMatrixCanBeMult(Matrix mainMatrix, Matrix anotherMatrix) {
        boolean check = false;
        if (mainMatrix.getAmountOfColumns() != anotherMatrix.getAmountOfRows())
            throw new MatrixException("Columns amount of first and rows amount of second matrices aren't equal");
        else
            check = true;
        return check;
    }

    public boolean isReceivedMatrixCanBeMult(Matrix anotherMatrix) {
        return isReceivedMatrixCanBeMult(this, anotherMatrix);
    }

    public int getAmountOfRows() {
        return matrix.length;
    }

    public int getAmountOfColumns() {
        return matrix[0].length;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(matrix);
    }
}
