package Task9Test;

import main.ua.university.Task9.Matrix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MatrixTest {

    Matrix mainMatrix;
    Matrix secondMatrix;

    @BeforeEach
    void setMatrix() {
        mainMatrix = new Matrix(MatrixDefaults.mainMatrix3x3.clone());
        secondMatrix = new Matrix(MatrixDefaults.secondMatrix3x3.clone());
    }

    @Test
    void setElemByIndex() {
        mainMatrix.setElemByIndex(69,1,1);
        Assertions.assertArrayEquals(MatrixDefaults.resultAfterChangeElement, mainMatrix.getMatrix());
    }

    @Test
    void addAnotherMatrix() {
        Assertions.assertTrue(mainMatrix.addAnotherMatrix(secondMatrix));
        Assertions.assertArrayEquals(MatrixDefaults.resultAfterAddition3x3, mainMatrix.getMatrix());
    }

    @Test
    void addingTwoMatrices() {
        Matrix test = mainMatrix.addingTwoMatrices(mainMatrix, secondMatrix);
        Assertions.assertArrayEquals(MatrixDefaults.resultAfterAddition3x3, test.getMatrix());
    }

    @Test
    void deductAnotherMatrix() {
        Assertions.assertTrue(mainMatrix.deductAnotherMatrix(secondMatrix));
        Assertions.assertArrayEquals(MatrixDefaults.resultAfterDeduction3x3, mainMatrix.getMatrix());
    }

    @Test
    void deductingTwoMatrices() {
        Matrix test = mainMatrix.deductingTwoMatrices(mainMatrix, secondMatrix);
        Assertions.assertArrayEquals(MatrixDefaults.resultAfterDeduction3x3, test.getMatrix());
    }

    @Test
    void multiAnotherMatrix() {
        mainMatrix.setMatrix(MatrixDefaults.mainMatrix2x3.clone());
        secondMatrix.setMatrix(MatrixDefaults.secondMatrixForMulti3x3.clone());
        Assertions.assertTrue(mainMatrix.multiAnotherMatrix(secondMatrix));
        Assertions.assertArrayEquals(MatrixDefaults.resultAfterMultiplication2x3, mainMatrix.getMatrix());
    }

    @Test
    void multipleTwoMatrices() {
        mainMatrix.setMatrix(MatrixDefaults.mainMatrix2x3.clone());
        secondMatrix.setMatrix(MatrixDefaults.secondMatrixForMulti3x3.clone());
        Matrix test = mainMatrix.multipleTwoMatrices(mainMatrix, secondMatrix);
        Assertions.assertArrayEquals(MatrixDefaults.resultAfterMultiplication2x3, test.getMatrix());
    }

    @Test
    void MultipleTwoMatricesArrays() {
        mainMatrix.setMatrix(MatrixDefaults.mainMatrix2x3.clone());
        secondMatrix.setMatrix(MatrixDefaults.secondMatrixForMulti3x3.clone());
        double[][] test = mainMatrix.multipleTwoMatrices(mainMatrix.getMatrix(), secondMatrix.getMatrix());
        Assertions.assertArrayEquals(MatrixDefaults.resultAfterMultiplication2x3, test);
    }

    @Test
    void getAmountOfRows() {
        int amount = mainMatrix.getMatrix().length;
        Assertions.assertEquals(amount, mainMatrix.getAmountOfRows());
    }

    @Test
    void getAmountOfColumns() {
        int amount = mainMatrix.getMatrix()[0].length;
        Assertions.assertEquals(amount, mainMatrix.getAmountOfColumns());
    }

    @Test
    void whenAddingAndReceivedMatrixHaveDiffRowsAmountThenThrowException() {
        secondMatrix.setMatrix(MatrixDefaults.mainMatrix2x3);

        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> {
            mainMatrix.isReceivedMatrixCanBeAdd(secondMatrix);
        });
        String expectedMessage = "Rows amount isn't equal";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void whenAddingAndReceivedMatrixHaveDiffColumnsAmountThenThrowException() {
        secondMatrix.setMatrix(MatrixDefaults.mainMatrix3x2);

        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> {
            mainMatrix.isReceivedMatrixCanBeAdd(secondMatrix);
        });
        String expectedMessage = "Columns amount isn't equal";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void whenMultiAndMainMatrixHaveDiffColumnsAmountThenThrowException() {
        mainMatrix.setMatrix(MatrixDefaults.mainMatrix2x2);

        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> {
            mainMatrix.isReceivedMatrixCanBeMult(secondMatrix);
        });
        String expectedMessage = "Columns amount of first and rows amount of second matrices aren't equal";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void whenMultiAndSecondMatrixHaveDiffRowsAmountThenThrowException() {
        secondMatrix.setMatrix(MatrixDefaults.mainMatrix2x2);
        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> {
            mainMatrix.isReceivedMatrixCanBeMult(secondMatrix);
        });
        String expectedMessage = "Columns amount of first and rows amount of second matrices aren't equal";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void whenMultipleTwoMatrixGetTrue() {
        Assertions.assertTrue(mainMatrix.isReceivedMatrixCanBeMult(mainMatrix, secondMatrix));
    }

    @Test
    void whenMultipleTwoDifferentMatrixGetTrue() {
        mainMatrix.setMatrix(MatrixDefaults.mainMatrix2x3);
        Assertions.assertTrue(mainMatrix.isReceivedMatrixCanBeMult(mainMatrix, secondMatrix));
    }

}