package main.ua.advanced.practice3.task3_special_collections.median_queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class MedianQueueTest {
    IMedianQueue queue;

    @BeforeEach
    void setQueue() {
        queue = new MedianQueue();
    }

    @Test
    void offerOneElement() {
        queue.offer(1);
        assertEquals("[1]", queue.toString());
    }

    @Test
    void offerTwoElements() {
        queue.offer(1);
        queue.offer(-2);
        assertEquals("[1, -2]", queue.toString());
    }

    @Test
    void offerThreeElements() {
        queue.offer(1);
        queue.offer(-2);
        queue.offer(25);
        assertEquals("[1, -2, 25]", queue.toString());
    }

    @Test
    void offerTenElements() {
        queue.offer(1);
        queue.offer(-2);
        queue.offer(25);
        queue.offer(-25);
        queue.offer(0);
        queue.offer(-6);
        queue.offer(101);
        queue.offer(13);
        queue.offer(0);
        queue.offer(1);

        assertEquals(
                "[1, -2, 25, -25, 0, -6, 101, 13, 0, 1]", queue.toString());
    }

    @Test
    void peekEvenSizeQueue() {
        queue.offer( 100);
        queue.offer(10);
        queue.offer(123);
        queue.offer(-4);
        queue.offer(-2);
        queue.offer(1);
        assertEquals(1, queue.peek());
    }

    @Test
    void peekNotEvenSizeQueue() {
        queue.offer( 100);
        queue.offer(10);
        queue.offer(123);
        queue.offer(-4);
        queue.offer(-2);
        assertEquals(10, queue.peek());
    }

    @Test
    void peeOneSizeQueue() {
        queue.offer( 100);
        assertEquals(100, queue.peek());
    }

    @Test
    void peekZeroSizeQueue() {
        assertEquals(0, queue.peek());
    }

    @Test
    void pollUntilEnd() {
        offerSevenElements();
        assertEquals(10, queue.poll());
        assertEquals(1, queue.poll());
        assertEquals(14, queue.poll());
        assertEquals(-2, queue.poll());
        assertEquals(100, queue.poll());
        assertEquals(-4, queue.poll());
        assertEquals(123, queue.poll());
    }

    @Test
    void size() {
        offerSevenElements();
        assertEquals(7, queue.size());
    }

    @Test
    void iteratorNextAll() {
        offerSevenElements();
        Iterator<Integer> iterator = queue.iterator();
        assertEquals(100, iterator.next());
        assertEquals(10, iterator.next());
        assertEquals(123, iterator.next());
        assertEquals(-4, iterator.next());
        assertEquals(-2, iterator.next());
        assertEquals(1, iterator.next());
        assertEquals(14, iterator.next());
    }

    @Test
    void getExceptionWhenIteratorNextLast() {
        offerSevenElements();
        Iterator<Integer> iterator = queue.iterator();
        for (int i = 0; i < queue.size(); i++) {
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

    void offerSevenElements() {
        queue.offer( 100);
        queue.offer(10);
        queue.offer(123);
        queue.offer(-4);
        queue.offer(-2);
        queue.offer(1);
        queue.offer(14);
    }
}