package main.ua.advanced.practice3.task1_integerSet;

import java.util.Iterator;

public interface IntegerSet<E> {
    //adds a range of values into the set (first argument - inclusive, last argument - exclusive). Return true if any of range values were actually added.
    boolean add(E fromInclusive, E toExclusive);
    //adds a single value into the set
    boolean add (E value);

    //removes a range of values from the list (first argument - inclusive, last argument - exclusive). Return true if any of range values were actually removed.
    boolean remove(E fromInclusive, E toExclusive);
    //removes a single value from the set
    boolean remove(E value);

    Iterator<E> iterator();

    int size();
}
