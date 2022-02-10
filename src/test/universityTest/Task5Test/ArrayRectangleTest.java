package test.universityTest.Task5Test;

import main.ua.university.Task5.ArrayRectangle;
import main.ua.university.Task5.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayRectangleTest {
    ArrayRectangle rectangles;

    @BeforeEach
    void setRectangle() {
        rectangles = new ArrayRectangle(3);
    }

    @Test
    void isFreeSpace() {
        assertTrue(rectangles.isFreeSpace());
        rectangles = new ArrayRectangle(new Rectangle());
        assertFalse(rectangles.isFreeSpace());
    }

    @Test
    void findFreeIndex() {
        rectangles = new ArrayRectangle(new Rectangle(), new Rectangle());
        assertEquals(-1, rectangles.findFreeIndex());

        rectangles = new ArrayRectangle(new Rectangle(), null);
        assertEquals(1, rectangles.findFreeIndex());

        rectangles = new ArrayRectangle(2);
        assertEquals(0, rectangles.findFreeIndex());
    }

    @Test
    void addRectangle() {
        assertTrue(rectangles.isFreeSpace());

        rectangles.addRectangle(new Rectangle());
        rectangles.addRectangle(new Rectangle());
        rectangles.addRectangle(new Rectangle());

        assertFalse(rectangles.isFreeSpace());
    }

    @Test
    void numberMaxArea() {
        rectangles.addRectangle(new Rectangle(1,2));
        rectangles.addRectangle(new Rectangle(3,5));
        rectangles.addRectangle(new Rectangle(2,4));
        assertEquals(1, rectangles.numberMaxArea());
    }

    @Test
    void numberMinPerimeter() {
        rectangles.addRectangle(new Rectangle(1,2));
        rectangles.addRectangle(new Rectangle(3,5));
        rectangles.addRectangle(new Rectangle(10,12));
        assertEquals(0, rectangles.numberMinPerimeter());
    }

    @Test
    void numberSquare() {
        assertEquals(0, rectangles.numberSquare());

        rectangles.addRectangle(new Rectangle(2,2));
        rectangles.addRectangle(new Rectangle(3,5));
        rectangles.addRectangle(new Rectangle(4,4));
        assertEquals(2, rectangles.numberSquare());
    }
}
