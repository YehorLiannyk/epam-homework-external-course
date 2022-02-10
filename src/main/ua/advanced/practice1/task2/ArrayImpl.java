package main.ua.advanced.practice1.task2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {

    private Object[] objects = new Object[0];
    private int currentPosition = -1;

    class IteratorImpl implements Iterator<Object> {
        @Override
        public boolean hasNext() {
           //return objects[currentPosition + 1] != null;
            return objects.length > currentPosition + 1;
        }

        @Override
        public Object next() {
            if (hasNext()) {
                currentPosition++;
                return objects[currentPosition];
            } else
                throw new NoSuchElementException();
        }
    }

    @Override
    public void add(Object element) {
        checkIfElementNull(element);
        objects = increaseArrayByOne(objects);
        objects[objects.length - 1] = element;
    }

    private Object[] increaseArrayByOne(Object[] objects) {
        Object[] newArray = new Object[objects.length + 1];
        System.arraycopy(objects, 0, newArray, 0, objects.length);
        return newArray;
    }

    @Override
    public void set(int index, Object element) {
        checkIndexCompatibility(index);
        checkIfElementNull(element);
        objects[index] = element;
    }

    private void checkIfElementNull(Object element) {
        if (element == null) throw new NullPointerException();
    }

    private void checkIndexCompatibility(int index) {
        if (index >= objects.length || index < 0) throw new IndexOutOfBoundsException();
    }

    @Override
    public Object get(int index) {
        checkIndexCompatibility(index);
        Object element = objects[index];
        checkIfElementNull(element);
        return element;
    }

    @Override
    public int indexOf(Object element) {
        checkIfElementNull(element);
        int index = -1;
        for (int i = 0; i < objects.length; i++) {
            if (objects[i].equals(element))
                index = i;
        }
        return index;
    }

    @Override
    public void remove(int index) {
        checkIndexCompatibility(index);
        Object[] tempObjects = createArrayWithoutSomeElementByIndex(index);
        objects = tempObjects;
    }

    private Object[] createArrayWithoutSomeElementByIndex(int index) {
        Object[] tempObjects = new Object[objects.length - 1];
        int k = 0;
        for (int i = 0; i < objects.length; i++) {
            if (i != index) {
               tempObjects[k] = objects[i];
               k++;
            }
        }
        return tempObjects;
    }

    @Override
    public void clear() {
        objects = new Object[0];
    }

    @Override
    public int size() {
        return objects.length;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < objects.length; i++) {
            if (i + 1 == objects.length)
                sb.append(objects[i]);
            else
                sb.append(objects[i]).append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        ArrayImpl array = getStandardArray();
        printArray(array);

        //Container`s methods
        System.out.println("Container's methods below:");
        System.out.println("Size: " + array.size());
        System.out.println("Clear");
        array.clear();
        printArray(array);

        //Array's methods
        array = getStandardArray();
        System.out.println("Array's methods below:");
        System.out.println("Add \"X\" to array");
        array.add("X");
        printArray(array);
        System.out.println("Set 3 element to \"@\"");
        array.set(3, "@");
        printArray(array);
        System.out.println("Get 3 element: " + array.get(3));
        System.out.println("IndexOf element \"@\" : " + array.indexOf("@"));
        System.out.println("Remove 3 element");
        array.remove(3);
        printArray(array);

        //Iterator's methods
        array = getStandardArray();
        System.out.println("First hasNext(): " + array.iterator().hasNext());
        System.out.println("First element: " + array.iterator().next());
        System.out.println("Second hasNext(): " + array.iterator().hasNext());
        System.out.println("Second element: " + array.iterator().next());
        System.out.println("Third hasNext(): " + array.iterator().hasNext());
        System.out.println("Third element: " + array.iterator().next());
        try {
            System.out.println("Fourth hasNext(): " + array.iterator().hasNext());
            System.out.println("Fourth element: " + array.iterator().next());

        } catch (NoSuchElementException e) {
            System.out.println("Fourth element: NoSuchElementException");
        }
    }

    private static ArrayImpl getStandardArray() {
        ArrayImpl array = new ArrayImpl();
        array.add("A");
        array.add("B");
        array.add("C");
        return array;
    }

    private static void printArray(ArrayImpl array) {
        System.out.println("Array now: " + "\n" + array);
    }
}
