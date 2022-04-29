package main.ua.advanced.practice6.decorator;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class BaseDecoratorSubList implements ISubList {
    protected List<String> subList;

    protected BaseDecoratorSubList(List<String> subList) {
        this.subList = subList;
    }

    @Override
    public int size() {
        return subList.size();
    }

    @Override
    public Iterator<String> iterator() {
        return subList.iterator();
    }

    @Override
    public String get(int index) {
        return subList.get(index);
    }

    @Override
    public List<String> getList() {
        return subList;
    }

    /////////////////////////////////////////////
    // methods below is not used in the task but just need for implementation of List<> Interface
    /////////////////////////////////////////////

    @Override
    public boolean isEmpty() {
        return subList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return subList.contains(o);
    }

    @Override
    public Object[] toArray() {
        return subList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return subList.toArray(a);
    }

    @Override
    public boolean add(String s) {
        return subList.add(s);
    }

    @Override
    public boolean remove(Object o) {
        return subList.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return subList.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        return subList.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        return subList.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return subList.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return subList.removeAll(c);
    }

    @Override
    public void clear() {
        subList.clear();
    }

    @Override
    public String set(int index, String element) {
        return subList.set(index, element);
    }

    @Override
    public void add(int index, String element) {
        subList.add(index, element);
    }

    @Override
    public String remove(int index) {
        return subList.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return subList.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return subList.lastIndexOf(o);
    }

    @Override
    public ListIterator<String> listIterator() {
        return subList.listIterator();
    }

    @Override
    public ListIterator<String> listIterator(int index) {
        return subList.listIterator(index);
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        return subList.subList(fromIndex, toIndex);
    }

    @Override
    public String toString() {
        return subList.toString();
    }

    /////////////////////////////////////////////
    // methods above is not used in the task but just need for implementation of List<> Interface
    /////////////////////////////////////////////
}
