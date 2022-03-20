package main.ua.advanced.practice3.task2_cappedMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.AbstractSet;

import static org.junit.jupiter.api.Assertions.*;

class IntStringCappedMapTest {

    IntStringCappedMap map;

    @BeforeEach
    void setMap() {
        map = new IntStringCappedMap();
    }

    @Test
    void putOneElement() {
        map.put(10, "ten");
        assertEquals("[{10 -> \"ten\"}]", map.toString());
    }

    @Test
    void putTwoElements() {
        map.put(10, "ten");
        map.put(1, "one");
        assertEquals("[{10 -> \"ten\"}, {1 -> \"one\"}]", map.toString());
    }

    @Test
    void putThreeElements() {
        map.put(10, "ten");
        map.put(1, "one");
        map.put(5, "five");
        assertEquals("[{10 -> \"ten\"}, {1 -> \"one\"}, {5 -> \"five\"}]", map.toString());
    }

    @Test
    void entrySetMap() {
        map.put(10, "ten");
        map.put(1, "one");
        AbstractSet<Object> set = map.entrySetMap();
        assertEquals("[{10 -> \"ten\"}, {1 -> \"one\"}]", set.toString());
    }

    @Test
    void entrySetMapZeroElements() {
        AbstractSet<Object> set = map.entrySetMap();
        assertEquals("[]", set.toString());
    }

    @Test
    void get() {
        map.put(10, "ten");
        assertEquals("ten", map.get(10));
    }

    @Test
    void getNull() {
        map.put(10, "ten");
        assertNull(map.get(1));
    }

    @Test
    void removeByKeyCheckGettingPrevValue() {
        map.put(10, "ten");
        map.put(1, "one");
        map.put(5, "five");
        assertEquals("one", map.remove(1), "After removing get a wrong prev value");
    }

    @Test
    void removeByKey() {
        map.put(10, "ten");
        map.put(1, "one");
        map.put(5, "five");
        map.remove(1);
        assertEquals("[{10 -> \"ten\"}, {5 -> \"five\"}]", map.toString());
    }

    @Test
    void removeByKeyAndValue() {
        map.put(10, "ten");
        map.put(1, "one");
        map.put(5, "five");
        map.remove(1, "one");
        assertEquals("[{10 -> \"ten\"}, {5 -> \"five\"}]", map.toString());
    }

    @Test
    void sizeZero() {
        assertEquals(0, map.size());
    }

    @Test
    void size() {
        map.put(10, "ten");
        map.put(1, "one");
        map.put(5, "five");
        assertEquals(3, map.size());
    }

    @Test
    void sizeAfterRemoving() {
        map.put(10, "ten");
        map.put(1, "one");
        map.put(5, "five");
        map.remove(5);
        assertEquals(2, map.size());
    }
}