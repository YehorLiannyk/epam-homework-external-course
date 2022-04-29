package main.ua.advanced.practice6.decorator;

import java.util.Iterator;
import java.util.List;

public interface ISubList extends Iterable<String>, List<String> {

    int size();

    Iterator<String> iterator();

    String get(int index);

    List<String> getList();

    String toString();
}
