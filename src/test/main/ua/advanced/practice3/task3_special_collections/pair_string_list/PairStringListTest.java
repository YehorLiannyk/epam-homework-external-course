package main.ua.advanced.practice3.task3_special_collections.pair_string_list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class PairStringListTest {
    IPairStringList list;

    @BeforeEach
    void setList() {
        list = new PairStringList();
    }

    void fillList() {
        list.add("One");
        list.add("Two");
        list.add("Three");
    }

    @Test
    void addOneElement() {
        list.add("One");
        assertEquals("[One, One]", list.toString());
    }

    @Test
    void addTwoElements() {
        list.add("One");
        list.add("Two");
        assertEquals("[One, One, Two, Two]", list.toString());
    }

    @Test
    void addThreeElements() {
        list.add("One");
        list.add("Two");
        list.add("Three");
        assertEquals("[One, One, Two, Two, Three, Three]", list.toString());
    }

    @Test
    void addTwelveElements() {
        for (int i = 0; i < 4; i++) {
            list.add("One");
            list.add("Two");
            list.add("Three");
        }
        assertEquals(
                "[One, One, Two, Two, Three, Three" +
                ", One, One, Two, Two, Three, Three" +
                ", One, One, Two, Two, Three, Three" +
                ", One, One, Two, Two, Three, Three]",
                list.toString()
        );
    }

    @Test
    void addByIndexWhenIndexFree() {
        fillList();
        list.add("NEW", 6);
        assertEquals("[One, One, Two, Two, Three, Three, NEW, NEW]", list.toString());

    }

    @Test
    void addByIndexWhenIndexIsReserved() {
        fillList();
        list.add("NEW", 3);
        assertEquals("[One, One, Two, NEW, NEW, Two, Three, Three]", list.toString());
    }

    @Test
    void getExceptionAddingByIndexWhenIndexIsNegative() {
        fillList();
        String msg = "";
        try {
            list.add("NEW", -1);
        } catch (Exception e) {
            msg = e.toString();
        }
        assertTrue(msg.contains("ArrayIndexOutOfBoundsException"));
    }

    @Test
    void addByIndexWhenIndexIsBiggerThanSize() {
        fillList();
        String msg = "";
        try {
            list.add("NEW", 15);
        } catch (Exception e) {
            msg = e.toString();
        }
        assertTrue(msg.contains("IndexOutOfBoundsException"));
    }

    @Test
    void removeFirstByValue() {
        fillList();
        list.remove("One");
        assertEquals("[Two, Two, Three, Three]", list.toString());
    }

    @Test
    void removeSecondByValue() {
        fillList();
        list.remove("Two");
        assertEquals("[One, One, Three, Three]", list.toString());
    }

    @Test
    void removeThirdByValue() {
        fillList();
        list.remove("Three");
        assertEquals("[One, One, Two, Two]", list.toString());
    }

    @Test
    void getExceptionRemoveNotExistingValue() {
        fillList();
        String msg = "";
        try {
            list.remove("NONE");
        } catch (Exception e) {
            msg = e.toString();
        }
        assertTrue(msg.contains("NoSuchElementException"));
    }

    @Test
    void removeFirstByIndex() {
        fillList();
        list.remove(0);
        assertEquals("[Two, Two, Three, Three]", list.toString());
    }

    @Test
    void removeSecondByIndex() {
        fillList();
        list.remove(2);
        assertEquals("[One, One, Three, Three]", list.toString());
    }

    @Test
    void removeThirdByIndex() {
        fillList();
        list.remove(4);
        assertEquals("[One, One, Two, Two]", list.toString());
    }

    @Test
    void getExceptionRemoveNotExistingIndex() {
        fillList();
        String msg = "";
        try {
            list.remove(15);
        } catch (Exception e) {
            msg = e.toString();
        }
        assertTrue(msg.contains("ArrayIndexOutOfBoundsException"));
    }

    @Test
    void getFirst() {
        fillList();
        assertEquals("One", list.get(0));
    }

    @Test
    void getSecond() {
        fillList();
        assertEquals("Two", list.get(2));
    }

    @Test
    void getThird() {
        fillList();
        assertEquals("Three", list.get(4));
    }

    @Test
    void getExceptionGettingNoneExistingElement() {
        fillList();
        String msg = "";
        try {
            list.get(15);
        } catch (Exception e) {
            msg = e.toString();
        }
        assertTrue(msg.contains("ArrayIndexOutOfBoundsException"));
    }

    @Test
    void addCollection() {
        fillList();
        IPairStringList newList = new PairStringList();
        newList.add("Eno");
        newList.add("Owt");
        newList.add("Eerhtt");
        list.addCollection(newList);
        assertEquals("[One, One, Two, Two, Three, Three, Eno, Eno, Owt, Owt, Eerhtt, Eerhtt]", list.toString());
    }

    @Test
    void addCollectionByIndex() {
        fillList();
        IPairStringList newList = new PairStringList();
        newList.add("Eno");
        newList.add("Owt");
        newList.add("Eerhtt");
        list.addCollectionByIndex(newList, 3);
        assertEquals("[One, One, Two, Eno, Eno, Owt, Owt, Eerhtt, Eerhtt, Two, Three, Three]", list.toString());
    }

    @Test
    void iteratorNextThree() {
        fillList();
        Iterator<String> iterator = list.iterator();
        assertEquals("One", iterator.next());
        assertEquals("One", iterator.next());
        assertEquals("Two", iterator.next());
        assertEquals("Two", iterator.next());
        assertEquals("Three", iterator.next());
        assertEquals("Three", iterator.next());
    }

    @Test
    void getExceptionWhenIteratorNextFour() {
        fillList();
        Iterator<String> iterator = list.iterator();
        for (int i = 0; i < list.size(); i++) {
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