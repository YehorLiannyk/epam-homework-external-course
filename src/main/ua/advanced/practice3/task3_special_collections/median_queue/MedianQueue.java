package main.ua.advanced.practice3.task3_special_collections.median_queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MedianQueue implements IMedianQueue {
    private final int DEFAULT_QUEUE_SIZE = 0;
    private int[] queue = new int[DEFAULT_QUEUE_SIZE];
    private int median;
    private int size = 0;

    @Override
    public boolean offer(int value) {
        if (size == queue.length)
            expandQueue();
        if (size == 0)
            median = value;
        queue[size++] = value;
        median = findMedian();
        return false;
    }

    private int findMedian() {
        int[] ascendQueue = getSortedIntegers(queue);
        if (ascendQueue.length % 2 != 0) {
            double index = (double) ascendQueue.length / 2 - 0.5;
            return ascendQueue[(int) index];
        }
        else
            return ascendQueue[(ascendQueue.length / 2 - 1)];
    }

    private int[] getSortedIntegers(int[] oldSet) {
        //Insertion sort
        int[] sortedArr = new int[oldSet.length];
        System.arraycopy(oldSet, 0, sortedArr, 0, oldSet.length);
        for (int i = 1; i < sortedArr.length; i++) {
            int temp;
            for (int j = i; j >= 0 ; j--) {
                if (j > 0) {
                    if (sortedArr[j - 1] > sortedArr[j]) {
                        temp = sortedArr[j - 1];
                        sortedArr[j - 1] = sortedArr[j];
                        sortedArr[j] = temp;
                    }
                }
            }
        }
        return sortedArr;
    }

    private void expandQueue() {
        int[] newArray = new int[queue.length + 1];
        System.arraycopy(queue, 0, newArray, 0, queue.length);
        queue = newArray;
    }

    @Override
    public int poll() {
        final int oldMedian = median;
        int index = findIndex(queue, median);
        removeByIndex(index);
        median = size != 0 ? findMedian() : 0;
        return oldMedian;
    }

    private int findIndex(int[] arr, int value) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == value)
                return i;
        }
        return -1;
    }

    private boolean removeByIndex(int index) {
        if (index >= size || index < 0)
            throw new NoSuchElementException();

        int[] result = new int[queue.length - 1];
        int k = 0;
        for (int i = 0; i < queue.length; i++)
            if (i != index)
                result[k++] = queue[i];
        queue = result;
        size--;
        return true;

    }

    @Override
    public int peek() {
        return size == 0 ? 0 : median;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            if (i + 1 == size)
                sb.append(queue[i]);
            else
                sb.append(queue[i]).append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int currentPos = 0;

            @Override
            public boolean hasNext() {
                return currentPos < size && currentPos > -1;
            }

            @Override
            public Integer next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                final int next = queue[currentPos];
                currentPos++;
                return next;
            }
        };
    }

    public static void main(String[] args) {
        IMedianQueue queue = new MedianQueue();

        System.out.println("Offering new elements: 1, 987, 4, 2, 3");
        queue.offer(1);
        queue.offer(987);
        queue.offer(4);
        queue.offer(2);
        queue.offer(3);

        printQueue(queue);
        printBorder();

        System.out.println("Peek element -> " + queue.peek());
        printQueue(queue);
        printBorder();

        System.out.println("Poll element (x1) -> " + queue.poll());
        printQueue(queue);
        System.out.println("Poll element (x2) -> " + queue.poll());
        printQueue(queue);
        printBorder();

        System.out.println("Size of queue = " + queue.size());
        printQueue(queue);
        printBorder();

        System.out.println("Iterator of queue:");
        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.println("Median: " + iterator.next());
            printQueue(queue);
        }
    }

    private static void printBorder() {
        for (int i = 0; i < 10; i++)
            System.out.print("==");
        System.out.println();
    }


    private static void printQueue(IMedianQueue queue) {
        System.out.println("IMedianQueue now:");
        System.out.println(queue.toString());
    }
}
