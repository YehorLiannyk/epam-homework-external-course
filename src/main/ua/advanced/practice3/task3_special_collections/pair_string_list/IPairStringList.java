package main.ua.advanced.practice3.task3_special_collections.pair_string_list;

import java.util.Iterator;

public interface IPairStringList extends Iterable<String> {

    void add (String value);

    void add (String value, int index);

    boolean remove (String value);

    boolean remove (int index);

    String get(int index);

    Iterator<String> iterator();
    int size();

    void addCollection(IPairStringList list);

    void addCollectionByIndex(IPairStringList list, int index);
}
