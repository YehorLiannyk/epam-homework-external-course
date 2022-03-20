package main.ua.advanced.practice3.task2_cappedMap;

import org.apache.xmlbeans.impl.piccolo.util.DuplicateKeyException;

import java.util.*;

public class IntStringCappedMap implements CappedMap<Integer, String> {
    private Entry[] map;

    private int size;
    private int capacity;
    private int currentCapacity;

    private static final int DEFAULT_CAPACITY = 100;
    private static final int DEFAULT_SIZE = 10;

    public IntStringCappedMap(int capacity) {
        this.capacity = capacity;
        currentCapacity = 0;
        map = new Entry[DEFAULT_SIZE];
    }

    public IntStringCappedMap() {
        this(DEFAULT_CAPACITY);
    }

    //AbstractSet<Object> entrySet() was clashes with Set<Map.Entry<Integer, String>> entrySet()
    //so I implemented entrySetMap() according to task
    // and implemented entrySet() just for extending Map<> interface

    @Override
    public AbstractSet<Object> entrySetMap() {
        return new AbstractSet<>() {
            @Override
            public Iterator<Object> iterator() {
                return iteratorSet();
            }

            @Override
            public int size() {
                return size;
            }
        };
    }

    private Iterator<Object> iteratorSet() {
        return new Iterator<>() {
            private int id = 0;
            private Entry next = map[id];

            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public Entry next() {
                if (hasNext()) {
                    final Entry result = next;
                    next = id < size ? map[++id] : null;
                    return result;
                }
                throw new NoSuchElementException();
            }
        };
    }

    @Override
    public Set<Map.Entry<Integer, String>> entrySet() {
        return new AbstractSet<>() {
            @Override
            public Iterator<Map.Entry<Integer, String>> iterator() {
                return iteratorMap();
            }

            @Override
            public int size() {
                return size;
            }
        };
    }

    private Iterator<Map.Entry<Integer, String>> iteratorMap() {
        return new Iterator<>() {
            private int id = 0;
            private Map.Entry<Integer, String> next = map[id];

            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public Map.Entry<Integer, String> next() {
                if (hasNext()) {
                    final Map.Entry<Integer, String> result = next;
                    next = id < size ? map[++id] : null;
                    return result;
                }
                throw new NoSuchElementException();
            }
        };
    }

    @Override
    public String get(Object key) {
        if (key.getClass() != Integer.class)
            throw new IllegalArgumentException();
        return get((Integer) key);
    }

    private String get(Integer key) {
        if (size == 0)
            return null;
        return findValueByKey(key);
    }

    @Override
    public String put(Integer key, String value) {
        if (value.length() > capacity)
            throw new IllegalArgumentException();
        if (containsKey(key))
            try {
                throw new DuplicateKeyException();
            } catch (DuplicateKeyException e) {
                e.printStackTrace();
            }

        Entry entry = new Entry(key, value);
        String oldValue = containsKey(key) ? findValueByKey(key) : null;
        clearOldElementsForNew(entry);

        if (size + 1 >= map.length)
            expandArray();

        map[size++] = entry;
        currentCapacity += entry.getLength();
        return oldValue;
    }

    private void expandArray() {
        int newSize = (map.length * 3) / 2 + 1;
        Entry[] entries = new Entry[newSize];
        System.arraycopy(map, 0, entries, 0, map.length);
        map = entries;
    }


    private boolean clearOldElementsForNew(Entry newEntry) {
        boolean check = false;
        while (currentCapacity + newEntry.getLength() > capacity) {
            check = removeOld();
        }
        return check;
    }

    private boolean removeOld() {
        if (size == 0 || map.length == 0)
            throw new NoSuchElementException();
        currentCapacity -= map[0].getLength();
        map[0] = null;
        size--;
        map = getArrayWithNullAtEnd(map);
        return true;
    }


    private Entry[] getArrayWithNullAtEnd(Entry[] oldArr) {
        Entry[] arr = new Entry[size];
        int k = 0;
        for (var el : oldArr) {
            if (el != null)
                arr[k++] = el;
        }
        return arr;
    }

    @Override
    public String remove(Object key) {
        if (key.getClass() != Integer.class)
            throw new IllegalArgumentException();
        return remove((int) key);
    }

    private String remove(int key) {
        if (!containsKey(key))
            throw new NoSuchElementException();
        int idForRemove = findIdInArrayByKey(key);
        String oldValue = map[idForRemove].getValue();
        currentCapacity -= map[idForRemove].getLength();
        map[idForRemove] = null;
        size--;
        map = getArrayWithNullAtEnd(map);
        return oldValue;
    }

    private int findIdInArrayByKey(int key) {
        for (int i = 0; i < map.length; i++) {
            if (map[i].getKey() == key)
                return i;
        }
        return -1;
    }

    private String findValueByKey(int key) {
        for (var entry : map) {
            if (entry != null )
                if (entry.getKey() == key)
                    return entry.getValue();
        }
        return null;
    }

    private Integer findKeyByValue(String value) {
        for (var entry : map) {
            if (entry != null)
                if (entry.getValue().equals(value))
                    return entry.getKey();
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> sourceMap) {
        sourceMap.forEach(this::put);
    }

    @Override
    public void clear() {
        map = new Entry[DEFAULT_SIZE];
        size = 0;
        currentCapacity = 0;
    }

    @Override
    public HashSet<Integer> keySet() {
        HashSet<Integer> set = new HashSet<>();
        for (var entry : map)
            if (entry != null)
                set.add(entry.getKey());
        return set;
    }


    @Override
    public ArrayList<String> values() {
        ArrayList<String> list = new ArrayList<>();
        for (var entry : map)
            if (entry != null)
                list.add(entry.getValue());
        return list;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        if (key.getClass() != Integer.class)
            throw new IllegalArgumentException();
        return containsKey((Integer) key);
    }

    private boolean containsKey(Integer key) {
        return findValueByKey(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        if (value.getClass() != String.class)
            throw new IllegalArgumentException();
        return containsValue((String) value);
    }

    private boolean containsValue(String value) {
        return findKeyByValue(value) != null;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            if (i + 1 == size)
                sb.append(map[i].toString());
            else
                sb.append(map[i].toString()).append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    private static class Entry implements Map.Entry<Integer, String> {
        private int key;
        private String value;
        private int length;

        public Entry(int key, String value) {
            this.key = key;
            this.value = value;
            length = value.length();
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String setValue(String value) {
            String oldValue = this.value;
            this.value = value;
            length = value.length();
            return oldValue;
        }

        public int getLength() {
            return length;
        }

        @Override
        public String toString() {
            return "{" + key + " -> " + '\"' + value + '\"' + "}";
        }
    }

    public static void main(String[] args) {
        IntStringCappedMap map = new IntStringCappedMap(25);
        map.put(5, "Five");
        map.put(6, "Six");
        map.put(7, "Seven");
        map.put(8, "Eight");
        map.put(12, "Twelve");
        map.put(9, "Nine");
        map.put(1, "One");

        System.out.println(new TreeMap<>(map));
        //{1=One, 7=Seven, 8=Eight, 9=Nine, 12=Twelve}
    }
}
