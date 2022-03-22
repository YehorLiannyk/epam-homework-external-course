package main.ua.advanced.practice3.task3_special_collections.absolute_value_int_set;

import java.util.Iterator;

public interface IAbsoluteValueIntSet extends Iterable<Integer>{
    boolean add (int value);

    boolean remove(int value);

    int searchIndex(int value);

    int size();

    int get(int index);

    Iterator<Integer> iterator();

    boolean addCollection(IAbsoluteValueIntSet nextSet);
}
