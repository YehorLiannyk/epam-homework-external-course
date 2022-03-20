package main.ua.advanced.practice3.task3_special_collections.pair_string_list;

import java.util.*;

public class PairStringList implements IPairStringList {
    String[] pairList = new String[DEFAULT_CAPACITY];
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int AMOUNT_OF_ELEMENTS_IN_PAIR = 2;

    private void expandArray() {
        int newSize = (pairList.length * 3) / 2 + 2;
        String[] list = new String[newSize];
        System.arraycopy(pairList, 0, list, 0, pairList.length);
        pairList = list;
    }

    @Override
    public void add(String value) {
        add(value, size);
    }

    @Override
    public void add(String value, int index) {
        if (index >= pairList.length)
            throw new IndexOutOfBoundsException();
        if (size + AMOUNT_OF_ELEMENTS_IN_PAIR >= pairList.length)
            expandArray();

        moveElementsInArrIfIndexIsReserved(index);

        for (int i = 0; i < pairList.length; i++) {
            if (i >= index && i < (index + AMOUNT_OF_ELEMENTS_IN_PAIR))
                pairList[i] = value;
        }

        size += AMOUNT_OF_ELEMENTS_IN_PAIR;
    }

    private void moveElementsInArrIfIndexIsReserved(int index) {
        if (checkIfIndexIsReserved(index))
            pairList = moveElementsInArrayFromIndex(pairList, index, PairStringList.AMOUNT_OF_ELEMENTS_IN_PAIR);
    }

    private String[] moveElementsInArrayFromIndex(String[] array, int srcIndex, int length) {
        String[] result = new String[array.length];
        for (var el : result)
            el = "";
        System.arraycopy(array, 0, result, 0, srcIndex);
        int destIndex = srcIndex + length;
        System.arraycopy(array, srcIndex, result, destIndex, array.length - destIndex);
        return result;
    }

    private boolean checkIfIndexIsReserved(int index) {
        if (index >= pairList.length)
            throw new IndexOutOfBoundsException();
        return pairList[index] != null;
    }

    @Override
    public boolean remove(String value) {
        if (!containsValue(value))
            throw new NoSuchElementException();

        int[] removeIndexes = getIndexesMatchValueInPair(value);
        pairList = getArrayAfterRemoveByIndexes(pairList, removeIndexes);
        size -= AMOUNT_OF_ELEMENTS_IN_PAIR;
        return true;
    }

    private String[] getArrayAfterRemoveByIndexes(String[] list, int[] indexes) {
        if (indexes == null || indexes[0] < 0 || indexes[indexes.length - 1] >= list.length)
            throw new IllegalArgumentException();

        String[] array = new String[list.length];
        int removeIndex = 0;
        int arrIndex = 0;
        boolean isElementsRemoved = false;
        for (int i = 0; i < array.length; i++) {
            if (isElementsRemoved || i != indexes[removeIndex])
                array[arrIndex++] = list[i];
            else
                if (++removeIndex == AMOUNT_OF_ELEMENTS_IN_PAIR)
                    isElementsRemoved = true;
        }
        return array;
    }


    /*private String[] getArrayAfterRemoveByIndexes(String[] list, int startIndex, int endIndex) {
        String[] array = new String[list.length];
        System.arraycopy(list, 0, array, 0, startIndex);
        System.arraycopy(list, startIndex, array, startIndex, endIndex - startIndex);
        return array;
    }*/

   /* private int getFirstIndexMatchValueInPair(String value) {
        int firstIndex = -1;
        int amountOfMatch = 0;
        for (int i = 0; i < pairList.length; i++) {
            if (pairList[i].equals(value)) {
                amountOfMatch++;
                if (amountOfMatch == AMOUNT_OF_ELEMENTS_IN_PAIR)
                    firstIndex = i - AMOUNT_OF_ELEMENTS_IN_PAIR;
            } else
                amountOfMatch = 0;
        }
        return firstIndex;
    }*/

    private int[] getIndexesMatchValueInPair(String value) {
        int amountOfMatch = 0;
        int[] matchIndexes = new int[AMOUNT_OF_ELEMENTS_IN_PAIR];
        for (int i = 0; i < pairList.length; i++) {
            if (pairList[i] != null && pairList[i].equals(value)) {
                matchIndexes[amountOfMatch++] = i;
            }
        }
        return matchIndexes;
    }

    @Override
    public boolean remove(int index) {
        if (!containsValue(index))
            throw new NoSuchElementException();

        int[] removeIndexes = getIndexesMatchValueInPair(get(index));
        pairList = getArrayAfterRemoveByIndexes(pairList, removeIndexes);
        size -= AMOUNT_OF_ELEMENTS_IN_PAIR;
        return true;
    }

    @Override
    public String get(int index) {
        if (!containsValue(index))
            throw new NoSuchElementException();
        return pairList[index];
    }

    @Override
    public Iterator<String> iterator() {
        return new IteratorClass();
    }

    @Override
    public int size() {
        return size;
    }

    private boolean containsValue(String value) {
        int amountOfMatches = 0;
        for (var item : pairList) {
            if (item != null && item.equals(value)) {
                amountOfMatches++;
                if (amountOfMatches == AMOUNT_OF_ELEMENTS_IN_PAIR)
                    return true;
            }
        }
        return false;
    }

    private boolean containsValue(int index) {
        int amountOfMatches = 0;
        String value = pairList[index];
        for (int i = 0; i < pairList.length; i++) {
            if (pairList[i].equals(value)) {
                amountOfMatches++;
                if (amountOfMatches == AMOUNT_OF_ELEMENTS_IN_PAIR)
                    return true;
            }
        }
        return false;
    }

    public void addCollection(IPairStringList list) {
        for (var item : list) {
            this.addCollectionItem(item);
        }
    }

    private void addCollectionItem(String value) {
        if (size + AMOUNT_OF_ELEMENTS_IN_PAIR >= pairList.length)
            expandArray();

        pairList[size++] = value;
    }

    private void addCollectionItemByIndex(String value, int index) {
        if (index >= pairList.length)
            throw new IndexOutOfBoundsException();
        if (size + AMOUNT_OF_ELEMENTS_IN_PAIR >= pairList.length)
            expandArray();

        moveElementsInArrIfIndexIsReserved(index);
        pairList[index] = value;
        size++;
    }

    public void addCollectionByIndex(IPairStringList list, int index) {
        for (var item : list) {
            this.addCollectionItemByIndex(item, index++);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            if (i + 1 == size)
                sb.append(pairList[i]);
            else
                sb.append(pairList[i]).append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    private class IteratorClass implements Iterator<String>{
        private int currentPos = 0;
        private String next = pairList[currentPos];

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public String next() {
            if (!hasNext())
                throw new NoSuchElementException();

            final String result = next;
            next = pairList[++currentPos];
            return result;
        }
    }

    public static void main(String[] args) {
        IPairStringList mainList = new PairStringList();
        System.out.println("Adding new elements");
        mainList = getMainList();
        printList(mainList);
        printBorder();

        System.out.println("Adding new element on index 4");
        mainList.add("NEW", 4);
        printList(mainList);
        printBorder();

        System.out.println("Remove NEW");
        mainList.remove("NEW");
        printList(mainList);
        printBorder();

        System.out.println("Remove at index 3");
        mainList.remove(3);
        printList(mainList);
        printBorder();

        System.out.println("Get value at index 2: " + mainList.get(2));
        printBorder();

        System.out.println("Create a new collection below");
        IPairStringList newList = new PairStringList();
        newList.add("Eno");
        newList.add("Owt");
        newList.add("Eerhtt");
        printList(newList);
        printBorder();

        System.out.println("Result of adding: ");
        mainList.addCollection(newList);
        printList(mainList);
        printBorder();

        mainList = getMainList();
        printList(mainList);
        System.out.println("Adding of collection at index 4");
        mainList.addCollectionByIndex(newList, 4);
        printList(mainList);
    }

    private static void printBorder() {
        for (int i = 0; i < 10; i++)
            System.out.print("==");
        System.out.println();
    }

    private static IPairStringList getMainList() {
        IPairStringList mainList = new PairStringList();
        mainList.add("One");
        mainList.add("Two");
        mainList.add("Three");
        mainList.add("Four");
        return mainList;
    }

    private static void printList(IPairStringList list) {
        System.out.println("PairList now:");
        System.out.println(list.toString());
    }
}
