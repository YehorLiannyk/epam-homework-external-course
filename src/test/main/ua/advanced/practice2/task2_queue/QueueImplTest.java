package main.ua.advanced.practice2.task2_queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class QueueImplTest {

    Queue list;

    @BeforeEach
    void setList() {
        list = new QueueImpl();
        list.enqueue("A");
        list.enqueue("B");
        list.enqueue("C");
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
    void enqueue() {
        list.enqueue("X");
        assertEquals("[A, B, C, X]", list.toString());
    }

    @Test
    void dequeue() {
        list.dequeue();
        assertEquals("[B, C]", list.toString());
    }

    @Test
    void top() {
        assertEquals("A", list.top());
    }
}