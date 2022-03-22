package main.ua.advanced.practice3.task3_special_collections.absolute_value_int_set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AbsoluteValueIntSetTest {
    IAbsoluteValueIntSet set;
    @BeforeEach
    void setList() {
        set = new AbsoluteValueIntSet();
    }

    void fillList() {
        set.add(1);
        set.add(0);
        set.add(15);
        set.add(-12);
    }

    @Test
    void addOneElement() {
        set.add(1);
        assertEquals("[1]", set.toString());
    }

    @Test
    void addTwoElements() {
        set.add(1);
        set.add(-2);
        assertEquals("[-2, 1]", set.toString());
    }

    @Test
    void addThreeElements() {
        set.add(1);
        set.add(-2);
        set.add(25);
        assertEquals("[-2, 1, 25]", set.toString());
    }

    @Test
    void addTenElements() {
        set.add(1);
        set.add(-2);
        set.add(25);
        set.add(-25);
        set.add(0);
        set.add(-6);
        set.add(101);
        set.add(13);
        set.add(0);
        set.add(1);

        assertEquals(
                "[-25, -6, -2, 0, 0, 1, 1, 13, 25, 101]", set.toString());
    }

    @Test
    void removeFirstByValue() {
        fillList();
        set.remove(-12);
        assertEquals("[0, 1, 15]", set.toString());
    }

    @Test
    void removeSecondByValue() {
        fillList();
        set.remove(0);
        assertEquals("[-12, 1, 15]", set.toString());
    }

    @Test
    void removeThirdByValue() {
        fillList();
        set.remove(1);
        assertEquals("[-12, 0, 15]", set.toString());
    }

    @Test
    void getExceptionRemoveNotExistingValue() {
        fillList();
        String msg = "";
        try {
            set.remove(99);
        } catch (Exception e) {
            msg = e.toString();
        }
        assertTrue(msg.contains("NoSuchElementException"));
    }

    @Test
    void getFirst() {
        fillList();
        assertEquals(-12, set.get(0));
    }

    @Test
    void getSecond() {
        fillList();
        assertEquals(0, set.get(1));
    }

    @Test
    void getFourth() {
        fillList();
        assertEquals(15, set.get(3));
    }

    @Test
    void getExceptionGettingNoneExistingElement() {
        fillList();
        String msg = "";
        try {
            set.get(12);
        } catch (Exception e) {
            msg = e.toString();
        }
        assertTrue(msg.contains("IndexOutOfBoundsException"));
    }

    @Test
    void addCollection() {
        fillList();
        IAbsoluteValueIntSet newList = new AbsoluteValueIntSet();
        newList.add(12);
        newList.add(-15);
        newList.add(0);
        newList.add(-1);
        set.addCollection(newList);
        assertEquals("[-15, -12, -1, 0, 0, 1, 12, 15]", set.toString());
    }

    @Test
    void searchIndex() {
        fillList();
        int search = set.searchIndex(15);
        assertEquals(3, search);
    }

    @Test
    void cantSearchIndex() {
        fillList();
        int search = set.searchIndex(-115);
        assertEquals(-1, search);
    }


    @Test
    void size() {
        fillList();
        assertEquals(4, set.size());
    }

    @Test
    void iteratorNextThree() {
        fillList();
        Iterator<Integer> iterator = set.iterator();
        assertEquals(-12, iterator.next());
        assertEquals(0, iterator.next());
        assertEquals(1, iterator.next());
        assertEquals(15, iterator.next());
    }

    @Test
    void getExceptionWhenIteratorNextFour() {
        fillList();
        Iterator<Integer> iterator = set.iterator();
        for (int i = 0; i < set.size(); i++) {
            iterator.next();
        }

        String msg = "";
        try {
            System.out.println(iterator.next());
        } catch (Exception e) {
            msg = e.toString();
        }
        assertTrue(msg.contains("NoSuchElementException"));
    }

}