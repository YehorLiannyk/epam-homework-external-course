package main.ua.advanced.practice2.task3_stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack{
    private Object[] stack;
    private Object top;
    private int size = 0;
    private final int MIN_STACK_SIZE = 10;

    public StackImpl() {
        stack = new Object[MIN_STACK_SIZE];
    }

    @Override
    public void clear() {
        stack =  new Object[MIN_STACK_SIZE];
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

    private void expandStack() {
        int newSize = (size * 3) / 2 + 1;
        Object[] newStack = new Object[newSize];
        System.arraycopy(stack, 0, newStack, 0, stack.length);
        stack = newStack;
    }

    @Override
    public void push(Object element) {
        if (size == stack.length)
            expandStack();
        top = element;
        stack[size++] = element;
    }

    @Override
    public Object pop() {
        if (size == 0)
            throw new NoSuchElementException();
        final Object oldTop = top;
        stack[size - 1] = null;
        size--;
        top = stack[size - 1];
        return oldTop;
    }

    @Override
    public Object top() {
        if (size == 0)
            throw new NoSuchElementException();
        return top;
    }

    @Override
    public Object[] reformatInObjectArray() {
        return stack;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            if (i + 1 == size)
                sb.append(stack[i]);
            else
                sb.append(stack[i]).append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    class IteratorImpl implements Iterator<Object> {
        private int currentPosition = size;

        @Override
        public boolean hasNext() {
            return (size > currentPosition - 1) && (currentPosition > 0);
        }

        @Override
        public Object next() {
            if (hasNext()) {
                return stack[--currentPosition];
            } else
                throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            if (currentPosition == -1 || stack[currentPosition] == null)
                throw new NoSuchElementException();
            if (currentPosition == size - 1)
                top = stack[size - 1];
            System.arraycopy(stack, currentPosition + 1, stack, currentPosition, stack.length - currentPosition - 1);
            size--;
        }
    }

    public static void main(String[] args) {
        Stack stack = getStandardStack();
        printStack(stack);

        //Container`s methods
        System.out.println("Container's methods below:");
        System.out.println("Size: " + stack.size());
        System.out.println("Clear");
        stack.clear();
        printStack(stack);

        //Stack's methods
        stack = getStandardStack();
        System.out.println("Stack's methods below:");

        System.out.println("Push \"X\"");
        stack.push("X");
        printStack(stack);

        System.out.println("Pop element: " + stack.pop());
        printStack(stack);

        System.out.println("Get top element: " + stack.top());

        //Iterator's methods
        System.out.println("Iterator's methods below:");

        stack = getStandardStack();
        Iterator<Object> iterator = stack.iterator();
        System.out.println("Try next x1: " + iterator.next());
        System.out.println("Try next x2: " + iterator.next());
        System.out.println("Remove the last element returned by this iterator");
        iterator.remove();
        printStack(stack);

        stack = getStandardStack();
        iterator = stack.iterator();
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

    private static Stack getStandardStack() {
        Stack stack = new StackImpl();
        stack.push("A");
        stack.push("B");
        stack.push("C");
        return stack;
    }

    private static void printStack(Stack stack) {
        System.out.println("Stack now: " + "\n" + stack);
    }
}
