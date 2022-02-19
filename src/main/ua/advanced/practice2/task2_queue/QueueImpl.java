package main.ua.advanced.practice2.task2_queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {
    private Object[] queue;
    private Object head;
    private int size = 0;
    private final int MIN_QUEUE_SIZE = 10;

    public QueueImpl() {
        queue = new Object[MIN_QUEUE_SIZE];
    }

    @Override
    public void clear() {
        queue =  new Object[MIN_QUEUE_SIZE];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private void expandQueue() {
        int newSize = (size * 3) / 2 + 1;
        Object[] newQueue = new Object[newSize];
        System.arraycopy(queue, 0, newQueue, 0, queue.length);
        queue = newQueue;
    }

    @Override
    public void enqueue(Object element) {
        if (size == queue.length)
            expandQueue();
        if (size == 0)
            head = element;
        queue[size++] = element;
    }

    @Override
    public Object dequeue() {
        final Object oldHead = head;
        System.arraycopy(queue, 1, queue, 0, queue.length - 1);
        head = queue[0];
        size--;
        return oldHead;
    }

    @Override
    public Object top() {
        if (head == null)
            throw new NoSuchElementException();
        return head;
    }

    @Override
    public Object[] reformatInObjectArray() {
        return queue;
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

    class IteratorImpl implements Iterator<Object> {
        private int currentPosition = -1;

        @Override
        public boolean hasNext() {
            return size > currentPosition + 1;
        }

        @Override
        public Object next() {
            if (hasNext()) {
                return queue[++currentPosition];
            } else
                throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            if (currentPosition == -1 || queue[currentPosition] == null)
                throw new NoSuchElementException();
            if (currentPosition == 0)
                head = queue[1];
            System.arraycopy(queue, currentPosition + 1, queue, currentPosition, queue.length - currentPosition - 1);
            size--;
        }
    }

    public static void main(String[] args) {
        Queue queue = getStandardQueue();
        printQueue(queue);

        //Container`s methods
        System.out.println("Container's methods below:");
        System.out.println("Size: " + queue.size());
        System.out.println("Clear");
        queue.clear();
        printQueue(queue);

        //Queue's methods
        queue = getStandardQueue();
        System.out.println("Queue's methods below:");

        System.out.println("Enqueue \"X\"");
        queue.enqueue("X");
        printQueue(queue);

        System.out.println("Dequeue");
        queue.dequeue();
        printQueue(queue);

        System.out.println("Get head element: " + queue.top());

        //Iterator's methods
        System.out.println("Iterator's methods below:");

        queue = getStandardQueue();
        Iterator<Object> iterator = queue.iterator();
        System.out.println("Try next x1: " + iterator.next());
        System.out.println("Try next x2: " + iterator.next());
        System.out.println("Remove the last element returned by this iterator");
        iterator.remove();
        printQueue(queue);

        queue = getStandardQueue();
        iterator = queue.iterator();
        System.out.println("First hasNext(): " + iterator.hasNext());
        System.out.println("First element: " + iterator.next());
        System.out.println("Second hasNext(): " + iterator.hasNext());
        System.out.println("Second element: " + iterator.next());
        System.out.println("Third hasNext(): " + iterator.hasNext());
        System.out.println("Third element: " + iterator.next());
        try {
            System.out.println("Fourth hasNext(): " + iterator.hasNext());
            System.out.println("Fourth element: " + iterator.next());

        } catch (NoSuchElementException e) {
            System.out.println("Fourth element: NoSuchElementException");
        }
    }

    private static Queue getStandardQueue() {
        Queue queue = new QueueImpl();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        return queue;
    }

    private static void printQueue(Queue queue) {
        System.out.println("Queue now: " + "\n" + queue);
    }
}
