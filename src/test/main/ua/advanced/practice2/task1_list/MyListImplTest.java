package main.ua.advanced.practice2.task1_list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class MyListImplTest {
    MyList list;

    @BeforeEach
    void setContainer() {
        list = new MyListImpl<String>();
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");
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
    void addFirst() {
        list.addFirst("X");
        assertEquals("[X, A, B, C]", list.toString());
    }

    @Test
    void addLast() {
        list.addLast("X");
        assertEquals("[A, B, C, X]", list.toString());
    }

    @Test
    void removeFirst() {
        list.removeFirst();
        assertEquals("[B, C]", list.toString());
    }

    @Test
    void removeLast() {
        list.removeLast();
        assertEquals("[A, B]", list.toString());
    }

    @Test
    void getFirst() {
        assertEquals("A", list.getFirst());
    }

    @Test
    void getLast() {
        assertEquals("C", list.getLast());
    }

    @Test
    void searchFirstDidNotGetNull() {
        assertNotNull(list.search("A"));
    }

    @Test
    void searchMiddleDidNotGetNull() {
        assertNotNull(list.search("A"));
    }

    @Test
    void searchLastDidNotGetNull() {
        assertNotNull(list.search("C"));
    }

    @Test
    void searchAndGetNull() {
        assertNull(list.search("X"));
    }

    @Test
    void remove() {
        list.remove("B");
        assertEquals("[A, C]", list.toString());
    }


    @Test
    void iteratorNextUntilException () {
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
}