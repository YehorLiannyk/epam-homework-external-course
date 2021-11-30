package Lesson7Task;
import static org.junit.jupiter.api.Assertions.*;

import main.ua.university.Lesson7Task.Number.Number;
import main.ua.university.Lesson7Task.Number.NumberController;
import main.ua.university.Lesson7Task.Number.NumberView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Lesson7TaskTest {

    NumberController numberController;

    @BeforeEach
    void setNumberController() {
        numberController = new NumberController(new Number(), new NumberView());
    }

    @Test
    void getRandomNumber() {
        boolean check = true;
        Number number = new Number();
        number.getRandomNumber();
        assertTrue(number.getNumber() <= number.getMax() || number.getNumber() >= number.getMin());
    }

    @Test
    void getNumberRange() {
        int[] arr = numberController.getNumberRange(67, 50, new int[] {0, 100});
        assertArrayEquals(new int[] {50, 100}, arr);

        arr = numberController.getNumberRange(67, 70, new int[] {0, 100});
        assertArrayEquals(new int[] {0, 70}, arr);

        arr = numberController.getNumberRange(67, 67, new int[] {0, 100});
        assertArrayEquals(new int[] {67, 67}, arr);
    }

    @Test
    void findFreeIndex() {
        int[][] arr = new int[2][];
        arr[0] = new int[0];
        arr[1] = new int[0];
        assertEquals(-1, numberController.findFreeIndex(arr));

        arr = new int[2][];
        assertEquals(0, numberController.findFreeIndex(arr));

        arr = new int[2][];
        arr[0] = new int[0];
        assertEquals(1, numberController.findFreeIndex(arr));
    }

    @Test
    void checkingRangeEquality() {
        assertFalse(numberController.checkingRangeEquality(new int[] {0, 100}));
        assertTrue(numberController.checkingRangeEquality(new int[] {55, 55}));
    }
}
