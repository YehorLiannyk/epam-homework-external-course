package Task4Test;

import main.ua.university.Task4.*;
import org.junit.jupiter.api.Test;
import main.ua.university.Task4.Main;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class Task4Test {

    @Test
    void isSorted() {
        int[] arr = {1, 2, 3};
        assertFalse( FirstTask.isSorted(arr, Main.SortOrder.DESCENDING),"Error in isSorted");
        assertTrue( FirstTask.isSorted(arr, Main.SortOrder.ASCENDING),"Error in isSorted");

        arr = new int[]{3, 2, 1};
        assertFalse( FirstTask.isSorted(arr, Main.SortOrder.ASCENDING),"Error in isSorted");
        assertTrue( FirstTask.isSorted(arr, Main.SortOrder.DESCENDING),"Error in isSorted");

        arr = new int[]{3, 5, 1};
        assertFalse( FirstTask.isSorted(arr, Main.SortOrder.ASCENDING),"Error in isSorted");
        assertFalse( FirstTask.isSorted(arr, Main.SortOrder.DESCENDING),"Error in isSorted");

    }

    @Test
    void transform() {
        int[] arr = {1, 2, 3, 4};
        assertArrayEquals(new int[]{1, 3, 5, 7}, SecondTask.transform(arr, Main.SortOrder.ASCENDING));

        arr = new int[] {1, 2, 3, 4};
        assertArrayEquals(new int[]{1, 2, 3, 4}, SecondTask.transform(arr, Main.SortOrder.DESCENDING));

        arr = new int[] {1, 3, 3, 4};
        assertArrayEquals(new int[]{1, 4, 5, 7}, SecondTask.transform(arr, Main.SortOrder.ASCENDING));

        arr = new int[] {1, 3, 3, 4};
        assertArrayEquals(new int[]{1, 3, 3, 4}, SecondTask.transform(arr, Main.SortOrder.DESCENDING));
    }

    @Test
    void createArithmeticProgression() {
        int[] arr = {5 , 8, 11, 14};
        assertArrayEquals(arr, ThirdTask.formArithmeticProgression(5, 3, 4));
    }

    @Test
    void multiArithmeticElements() {
        int[] arr = {5 , 8, 11, 14};
        assertEquals(6160, ThirdTask.multiArithmeticElements(arr));
    }

    @Test
    void formGeomProgression() {
        double[] arr = {100, 50, 25};
        int n = FourthTask.getNWithLoop(100, 0.5, 20);
        assertArrayEquals(arr, FourthTask.formGeometricProgression(100, 0.5, n));
    }

    @Test
    void sumOfGeomProgression() {
        double[] arr = {100, 50, 25};
        assertEquals(175, FourthTask.sumGeometricElements(arr));
    }

}
