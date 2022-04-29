package main.ua.advanced.practice6.iterator.timesIterators;

import main.ua.advanced.practice6.iterator.Iterators;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.logging.Level;

public class FiveTimesTimesIterator implements MyTimesIterator {
    private static final int TIMES_AMOUNT = 5;
    private int[] elements;
    private int current = 0;

    public FiveTimesTimesIterator(int[] elements) {
        this.elements = expandElements(elements);
    }

    private int[] expandElements(int[] sourceArr) {
        int[] expandedArray = new int[sourceArr.length * TIMES_AMOUNT];
        int arrIndex = 0;
        for (int item : sourceArr) {
            for (int j = 0; j < TIMES_AMOUNT; j++) {
                expandedArray[arrIndex++] = item;
            }
        }
        return expandedArray;
    }

    @Override
    public boolean hasNext() {
        return current < elements.length;
    }

    @Override
    public Integer next() {
        try {
            if (hasNext()) {
                return elements[current++];
            }
            throw new NoSuchElementException();
        } catch (NoSuchElementException e) {
            Iterators.logger.log(Level.SEVERE, Arrays.toString(e.getStackTrace()));
            throw new NoSuchElementException();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < elements.length; i++) {
            sb.append(elements[i]);
            if (i < elements.length - 1)
                sb.append(", ");
        }
        return sb.toString();
    }
}
