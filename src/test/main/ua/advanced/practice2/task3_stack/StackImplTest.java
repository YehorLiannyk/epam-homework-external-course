package main.ua.advanced.practice2.task3_stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class StackImplTest {
    Stack list;

    @BeforeEach
    void setList() {
        list = new StackImpl();
        list.push("A");
        list.push("B");
        list.push("C");
    }

    @Test
    void clear() {
        list.clear();
        assertEquals("[]", list.toString());
        assertEquals(0, list.size());
    }

    @Test
    void size() {
        assertEquals(3, list.size());
    }

    @Test
    void iterator() {
        Iterator<Object> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertNotNull(iterator.next());

        assertTrue(iterator.hasNext());
        assertNotNull(iterator.next());

        assertTrue(iterator.hasNext());
        assertNotNull(iterator.next());

        assertFalse(iterator.hasNext());
        String err = "";
        try {
            iterator.next();
        } catch (Exception e) {
            err = e.toString();
        }
        assertTrue(err.contains("NoSuchElementException"));
    }

    @Test
    void push() {
        list.push("X");
        assertEquals("[A, B, C, X]", list.toString());
    }

    @Test
    void pop() {
        assertEquals("C", list.pop());
        assertEquals("[A, B]", list.toString());
    }

    @Test
    void top() {
        assertEquals("C", list.top());
    }
}