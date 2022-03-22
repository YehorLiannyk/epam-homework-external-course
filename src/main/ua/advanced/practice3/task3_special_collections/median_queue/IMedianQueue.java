package main.ua.advanced.practice3.task3_special_collections.median_queue;

import java.util.Iterator;

public interface IMedianQueue extends Iterable<Integer>{
    boolean offer(int value);

    int poll();

    int peek();

    int size();

    Iterator<Integer> iterator();
}
