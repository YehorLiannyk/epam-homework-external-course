package main.ua.advanced.practice3.task3_special_collections.absolute_value_int_set;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class AbsoluteValueIntSet implements IAbsoluteValueIntSet {
    private int[] set = new int[DEFAULT_CAPACITY];
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 0;

    public AbsoluteValueIntSet() {}

    public AbsoluteValueIntSet(IAbsoluteValueIntSet intSet) {
        set = new int[DEFAULT_CAPACITY];
        size = 0;
        for (int i = 0; i < intSet.size(); i++) {
            this.add(intSet.get(i));
        }
    }

    private void increaseArrayByOne() {
        int[] newArray = new int[set.length + 1];
        System.arraycopy(set, 0, newArray, 0, set.length);
        set = newArray;
    }

    @Override
    public boolean add(int value) {
        if (size + 1 >= set.length)
            increaseArrayByOne();
        return addAndSort(value);
    }

    private boolean addAndSort(int value) {
        set[size] = value;
        size++;
        set = getSortedIntegers(set);
        return true;
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

    @Override
    public boolean remove(int value) {
        int index = findIndex(set, value);
        if (index != -1)
            return removeByIndex(index);
        else
            throw new NoSuchElementException();
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

        int[] result = new int[set.length - 1];
        int k = 0;
        for (int i = 0; i < set.length; i++)
            if (i != index)
                result[k++] = set[i];
        set = result;
        size--;
        return true;

    }

    @Override
    public int searchIndex(int value) {
        for (int i = 0; i < set.length; i++)
            if (set[i] == value)
                return i;
        return -1;
    }

    @Override
    public int get(int index) {
        if (index >= size || index >= set.length)
            throw new IndexOutOfBoundsException();
        return set[index];
    }

    @Override
    public int size() {
        return size;
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
                final int next = set[currentPos];
                currentPos++;
                return next;
            }
        };
    }

    @Override
    public boolean addCollection(IAbsoluteValueIntSet intSet) {
        for (int i = 0; i < intSet.size(); i++) {
            this.add(intSet.get(i));
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            if (i + 1 == size)
                sb.append(set[i]);
            else
                sb.append(set[i]).append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        IAbsoluteValueIntSet set;

        System.out.println("Adding new elements");
        set = getMainSet();
        printSet(set);
        printBorder();

        System.out.println("Remove 0");
        set.remove(0);
        printSet(set);
        printBorder();

        System.out.println("Get value at index 2: " + set.get(2));
        printBorder();

        System.out.println("Create a new collection below");
        IAbsoluteValueIntSet newSet = new AbsoluteValueIntSet();
        newSet.add(12);
        newSet.add(15);
        newSet.add(-100);
        printSet(newSet);
        printBorder();

        System.out.println("Result of adding: ");
        set.addCollection(newSet);
        printSet(set);
        printBorder();
    }

    private static void printBorder() {
        for (int i = 0; i < 10; i++)
            System.out.print("==");
        System.out.println();
    }

    private static IAbsoluteValueIntSet getMainSet() {
        IAbsoluteValueIntSet set = new AbsoluteValueIntSet();
        set.add(1);
        set.add(15);
        set.add(-2);
        set.add(0);
        return set;
    }

    private static void printSet(IAbsoluteValueIntSet set) {
        System.out.println("AbsoluteValueIntSet now:");
        System.out.println(set.toString());
    }
}
