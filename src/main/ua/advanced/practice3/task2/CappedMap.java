package main.ua.advanced.practice3.task2;

import java.util.AbstractSet;
import java.util.Map;

public interface CappedMap <K, V> extends Map<K, V> {

    //the method is partially implemented.
    //It returns an AbstractSet and you must only provide implementations for its iterator next and hasNext methods.
    AbstractSet<Object> entrySetMap();

    //return a value by its key
    V get(Object key);

    //set a value by a given key
    //If it leads to exceeding capacity, be sure to evict as many of the oldest elements as needed.
    V put(K key, V value);

    //removes a value by key.
    V remove(Object key);

    //returns number of map entries.
    int size();
}
