package universityTest.Task5Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {
    main.ua.university.Task5.Rectangle rectangle;

    @BeforeEach
    void setRectangle() {
        rectangle = new main.ua.university.Task5.Rectangle(4, 3);
    }

    @Test
    void getPerimeter() {
        assertEquals(14, rectangle.getPerimeter());
    }

    @Test
    void getArea() {
        assertEquals(12, rectangle.getArea());
    }

    @Test
    void isSquare() {
        Assertions.assertTrue(new main.ua.university.Task5.Rectangle(4, 4).isSquare());
        Assertions.assertFalse(new main.ua.university.Task5.Rectangle(2, 4).isSquare());
    }

    @Test
    void replaceSides() {
        rectangle.replaceSides();
        assertEquals(3, rectangle.getSideA());
        assertEquals(4, rectangle.getSideB());
    }
}
