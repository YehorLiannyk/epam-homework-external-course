package main.ua.advanced.practice1.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayImplTest {
    ArrayImpl array;

    @BeforeEach
    void setArray() {
        array = new ArrayImpl();
    }

    @Test
    void checkAdding() {
        array.add("A");
        array.add("B");
        assertEquals("[A, B]", array.toString());
    }

    @Test
    void checkAddingShouldReturnFalse() {
        settingArrWithThreeStandardElems();
        assertNotEquals("[A, B]", array.toString());
    }

    @Test
    void checkSettingFirstIndex() {
        settingArrWithThreeStandardElems();

        array.set(0, "X");
        assertEquals("[X, B, C]", array.toString());
    }

    @Test
    void checkSettingMiddleIndex() {
        settingArrWithThreeStandardElems();

        array.set(1, "X");
        assertEquals("[A, X, C]", array.toString());
    }

    @Test
    void checkSettingLastIndex() {
        settingArrWithThreeStandardElems();

        array.set(2, "X");
        assertEquals("[A, B, X]", array.toString());
    }

    private void settingArrWithThreeStandardElems() {
        array.add("A");
        array.add("B");
        array.add("C");
    }

    @Test
    void checkGettingFirstIndex() {
        settingArrWithThreeStandardElems();
        assertEquals("A", array.get(0));
    }

    @Test
    void checkGettingMiddleIndex() {
        settingArrWithThreeStandardElems();
        assertEquals("B", array.get(1));
    }

    @Test
    void checkGettingLastIndex() {
        settingArrWithThreeStandardElems();
        assertEquals("C", array.get(2));
    }

    @Test
    void checkIndexOfFirstElement() {
        settingArrWithThreeStandardElems();
        assertEquals(0, array.indexOf("A"));
    }

    @Test
    void checkIndexOfMiddleElement() {
        settingArrWithThreeStandardElems();
        assertEquals(1, array.indexOf("B"));
    }


    @Test
    void checkIndexOfLastElement() {
        settingArrWithThreeStandardElems();
        assertEquals(2, array.indexOf("C"));
    }

    @Test
    void checkRemovingFirstIndex() {
        settingArrWithThreeStandardElems();

        array.remove(0);
        assertEquals("[B, C]", array.toString());
    }

    @Test
    void checkRemovingMiddleIndex() {
        settingArrWithThreeStandardElems();

        array.remove(1);
        assertEquals("[A, C]", array.toString());
    }

    @Test
    void checkRemovingLastIndex() {
        settingArrWithThreeStandardElems();

        array.remove(2);
        assertEquals("[A, B]", array.toString());
    }

    @Test
    void clear() {
        settingArrWithThreeStandardElems();
        array.clear();
        assertEquals("[]", array.toString());
    }

    @Test
    void size() {
        settingArrWithThreeStandardElems();
        assertEquals(3, array.size());
    }

    @Test
    void testToString() {
        settingArrWithThreeStandardElems();
        assertEquals("[A, B, C]", array.toString());
    }
}