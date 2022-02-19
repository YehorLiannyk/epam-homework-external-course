package main.ua.advanced.practice2.task1_list;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyListImpl<E> implements MyList, Serializable {
    Node<E> first;
    Node<E> last;
    int size = 0;

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private void removeNode(Node<E> node) {
        final Node<E> next = node.next;
        final Node<E> prev = node.prev;
        node.element = null;
        node.next = null;
        node.prev = null;
        if (next != null)
            next.prev = prev;
        if (prev != null)
            prev.next = next;
        size--;
    }

    @Override
    public void addFirst(Object element) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>((E) element, f, null);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;

        /*if (first != head) {
            Node<E> node = getNewNode((E) element, head.next, head);
            head.next = node;
            head.next.prev = node;
        } else {
            setupFirstNode((E) element);
        }
        size++;*/
    }

    @Override
    public void addLast(Object element) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>((E) element, null, l);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;

        /*if (head.prev.equals(head)) {
            Node<E> newNode = getNewNode((E) element, head, head.prev);
            head.next = newNode;
            head.prev.next = newNode;
        } else {
            setupFirstNode((E) element);
        }
        size++;*/
    }

    /*
    private void setupFirstNode(E element) {
        Node<E> node = getNewNode(element, head, head);
        first = node;
        last = node;
    }*/

    @Override
    public void removeFirst() {
        final Node<E> f = first;
        if (f != null) {
            first = f.next;
            if (f.next == null)
                last = null;
            else
                f.next.prev = null;
            f.next = null;
            f.element = null;
            size--;
        } else
            throw new NoSuchElementException();
    }

    @Override
    public void removeLast() {
        final Node<E> l = last;
        if (l != null) {
            last = l.prev;
            if (l.prev == null)
                first = null;
            else
                l.prev.next = null;
            l.next = null;
            l.element = null;
            size--;
        } else
            throw new NoSuchElementException();
    }

    @Override
    public Object getFirst() {
        if (first != null)
            return first.element;
        else
            throw new NoSuchElementException();
    }

    @Override
    public Object getLast() {
        if (last != null)
            return last.element;
        else
            throw new NoSuchElementException();
    }

    @Override
    public Object search(Object element) {
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (node.element.equals(element))
                return node;
            else
                node = node.next;
        }
        return null;
    }

    @Override
    public boolean remove(Object element) {
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (node.element.equals(element)) {
                if (node.equals(first))
                    removeFirst();
                else if (node.equals(last))
                    removeLast();
                else
                    removeNode(node);
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public Object[] reformatInObjectArray() {
        Object[] objects = new Object[size];
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            objects[i] = node;
            if (node.next != null)
                node = node.next;
        }
        return objects;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> node = first ;
        sb.append("[");
        for (int i = 0; i < size; i++) {
            if (node != null) {
                if (i + 1 == size)
                    sb.append(node.element);
                else {
                    sb.append(node.element).append(", ");
                    node = node.next;
                }
            }
        }

        sb.append("]");
        return sb.toString();
    }


    static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        public Node(E element, Node<E> next, Node<E> prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
/*
        public Node(Node<E> newNode) {
            this.element = newNode.element;
            this.next = new Node<>(newNode.next);
            this.prev = new Node<>(newNode.prev);
        }*/
    }

    class IteratorImpl implements Iterator<Object> {
        private static int currentPosition = -1;

        @Override
        public boolean hasNext() {
            return size > currentPosition + 1;
        }

        @Override
        public Object next() {
            if (hasNext()) {
                Node<E> node = first;
                for (int i = 0; i < currentPosition + 1; i++) {
                    node = node.next;
                }
                currentPosition++;
                return node.element;
            } else
                throw new NoSuchElementException();
        }
    }

    public static void main(String[] args) {
        MyListImpl<String> list = getStandardList();
        printList(list);

        //Container`s methods
        System.out.println("Container's methods below:");
        System.out.println("Size: " + list.size());
        System.out.println("Clear");
        list.clear();
        printList(list);

        //MyList's methods
        list = getStandardList();
        System.out.println("MyList's methods below:");

        System.out.println("AddFirst \"Y\" to list");
        list.addFirst("Y");
        printList(list);
        System.out.println("AddLast \"X\" to list");
        list.addLast("X");
        printList(list);

        System.out.println("GetFirst of MyList : " + list.getFirst());
        System.out.println("GetLast of MyList : " + list.getLast());

        System.out.println("Search element \"Y\"");
        System.out.println(list.search("Y") != null ? "Found" : "Couldn't find");
        System.out.println("Search element \"@\"");
        System.out.println(list.search("@") != null ? "Found" : "Couldn't find");

        System.out.println("Remove first element \"Y\"");
        list.removeFirst();
        printList(list);
        System.out.println("Remove last element \"X\"");
        list.removeLast();
        printList(list);
        System.out.println("Remove element \"B\"");
        list.remove("B");
        printList(list);

        //Iterator's methods
        list = getStandardList();
        System.out.println("First hasNext(): " + list.iterator().hasNext());
        System.out.println("First element: " + list.iterator().next());
        System.out.println("Second hasNext(): " + list.iterator().hasNext());
        System.out.println("Second element: " + list.iterator().next());
        System.out.println("Third hasNext(): " + list.iterator().hasNext());
        System.out.println("Third element: " + list.iterator().next());
        try {
            System.out.println("Fourth hasNext(): " + list.iterator().hasNext());
            System.out.println("Fourth element: " + list.iterator().next());

        } catch (NoSuchElementException e) {
            System.out.println("Fourth element: NoSuchElementException");
        }
    }

    private static <E> MyListImpl getStandardList() {
        MyListImpl<E> list = new MyListImpl<>();
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");
        return list;

    }

    private static <E> void printList(MyListImpl<E> list) {
        System.out.println("MyList now: " + "\n" + list);
    }
}
