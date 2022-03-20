package main.ua.advanced.practice3.task1_integerSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class RangedOpsIntegerSetTest {

    RangedOpsIntegerSet set;
    Iterator<Integer> iterator;

    @BeforeEach
    void setSet() {
        set = new RangedOpsIntegerSet();
    }

    private void addRangeToSet() {
        set.add(1,5);
    }

    private void setIterator() {
        iterator = set.iterator();
    }

    @Test
    void addRange() {
        addRangeToSet();
        assertEquals("[1, 2, 3, 4]", set.toString());
    }

    @Test
    void addRangeGetException() {
        String err = "";
        try {
            set.add(5, -1);
        } catch (Exception e) {
            err = e.toString();
        }
        assertTrue(err.contains("IllegalArgumentException"));
    }

    @Test
    void addOneElement() {
        set.add(5);
        assertEquals("[5]", set.toString());
    }

    @Test
    void addExistingElement() {
        addRangeToSet();
        set.add(3);
        assertEquals("[1, 2, 3, 4]", set.toString());
    }

    @Test
    void removeRange() {
        addRangeToSet();
        set.remove(2, 4);
        assertEquals("[1, 4]", set.toString());
    }

    @Test
    void removeOneElement() {
        addRangeToSet();
        set.remove(4);
        assertEquals("[1, 2, 3]", set.toString());
    }

    @Test
    void removeNonExistingGetFalse() {
        addRangeToSet();
        assertFalse(set.remove(10));
    }

    @Test
    void iteratorHasNextGetTrue() {
        addRangeToSet();
        setIterator();
        for (int i = 0; i < set.size(); i++) {
            assertTrue(iterator.hasNext());
        }
    }

    @Test
    void iteratorHasNextGetFalse() {
        setIterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    void iteratorNext() {
        addRangeToSet();
        setIterator();
        for (int i = 0; i < set.size(); i++) {
            assertNotNull(iterator.next());
        }
    }

    @Test
    void iteratorNextGetException() {
        addRangeToSet();
        setIterator();
        for (int i = 0; i < set.size(); i++) {
            iterator.next();
        }

        String err = "";
        try {
            iterator.next();
        } catch (Exception e) {
            err = e.toString();
        }
        assertTrue(err.contains("NoSuchElementException"));
    }
}