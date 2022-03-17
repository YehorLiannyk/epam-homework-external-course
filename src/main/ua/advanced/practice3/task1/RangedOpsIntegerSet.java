package main.ua.advanced.practice3.task1;

import java.util.*;

public class RangedOpsIntegerSet implements IntegerSet<Integer> {
    private Integer[] set = new Integer[10];
    //Node<Integer> first;
    private int size = 0;

    private void expandArray() {
        int newSize = (set.length * 3) / 2 + 1;
        Integer[] newSet = new Integer[newSize];
        System.arraycopy(set, 0, newSet, 0, set.length);
        set = newSet;
    }

    @Override
    public boolean add(Integer fromInclusive, Integer toExclusive) {
        int amountOfNew = countSizeOfRange(fromInclusive, toExclusive);
        if (amountOfNew <= 0)
            throw new IllegalArgumentException();
        if (amountOfNew + size >= set.length)
            expandArray();

        boolean check = false;
        for (int i = fromInclusive; i < toExclusive; i++) {
            if(!checkIfExist(i)) {
                set[size++] = i;
                check = true;
            }
        }
        return check;
    }
    
    private boolean checkIfExist(Integer element) {
        boolean check = false;
        for (int i = 0; i < size; i++) {
            if (set[i].equals(element)) {
                check = true;
                break;
            }
        }
        return check;
    }

    private int countSizeOfRange(Integer fromInclusive, Integer toExclusive) {
        /*int amountOfNew = 0;
        for (int i = fromInclusive; i < toExclusive; i++)
            amountOfNew++;*/
        int amountOfNew = toExclusive - fromInclusive;
        return amountOfNew > 0 ? amountOfNew : 0;
    }

    @Override
    public boolean add(Integer value) {
        if (size + 1 >= set.length)
            expandArray();

        boolean check = false;
        if (!checkIfExist(value)) {
            set[size++] = value;
            check = true;
        }
        return check;
    }

    @Override
    public boolean remove(Integer fromInclusive, Integer toExclusive) {
        int countOfRange = countSizeOfRange(fromInclusive, toExclusive);
        if (countOfRange <= 0)
             throw new IllegalArgumentException();

        boolean check = false;
        for (Integer i = fromInclusive; i < toExclusive; i++) {
            int index = findIndex(set, i);
            if (index != -1)
                check = removeByIndex(index);
            set = cleanArrayOfNull(set);
        }
        return check;
    }

    private Integer[] cleanArrayOfNull(Integer[] oldArr) {
        Integer[] arr = new Integer[size];
        int k = 0;
        for (var el : oldArr) {
            if (el != null)
                arr[k++] = el;
        }
        return arr;
    }

    private boolean removeByIndex(int index) {
        if (index >= size || index < 0)
            throw new NoSuchElementException();

        set[index] = null;
        size--;
        return true;

    }

    private int findIndex(Integer[] arr, Integer value) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(value))
                return i;
        }
        return -1;
    }

    @Override
    public boolean remove(Integer value) {
        boolean check = false;
        int index = findIndex(set, value);
        if (index != -1)
            check = removeByIndex(index);
        return check;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new IteratorImpl();
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
                sb.append(set[i]);
            else
                sb.append(set[i]).append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    class IteratorImpl implements Iterator<Integer> {
        private int prevPosition = -1;
        private Integer lastElement;
        Integer[] sortedArr;

        @Override
        public boolean hasNext() {
            return size > prevPosition + 1;
        }

        @Override
        public Integer next() {
            if (hasNext()) {
                sortedArr = getSortedIntegers();
                Integer next = getNextIntInNaturalOrderByPrevIndex(prevPosition);
                lastElement = next;
                prevPosition++;
                return next;
            } else
                throw new NoSuchElementException();
        }

        private Integer getNextIntInNaturalOrderByPrevIndex(int prevIndex) {
            int indexOfLastIndex = findIndex(set, lastElement);

            int index = -1;
            if (lastElement == null || indexOfLastIndex == -1)
                index = prevIndex + 1;
            else
                index = findIndex(sortedArr, lastElement) + 1;
            return sortedArr[index];
        }

        private Integer[] getSortedIntegers() {
            //Insertion sort
            Integer[] sortedArr = Arrays.copyOf(set,set.length);
            for (int i = 1; i < sortedArr.length; i++) {
                Integer temp;
                for (int j = i; j >= 0 ; j--) {
                    if (j > 0 && sortedArr[j] != null) {
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
    }

    public static void main(String[] args) {
        RangedOpsIntegerSet set = new RangedOpsIntegerSet();
        set.add(1, 5);
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());
        System.out.println("=============");


        set = new RangedOpsIntegerSet();
        set.add(1, 5);
        set.add(9, 11);
        iterator = set.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());
        System.out.println("=============");

        set = new RangedOpsIntegerSet();
        set.add(1, 15);
        set.remove(3, 12);
        iterator = set.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());
        System.out.println("=============");

        set = new RangedOpsIntegerSet();
        set.add(1, 15);
        set.remove(3, 12);
        iterator = set.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());
    }
}
