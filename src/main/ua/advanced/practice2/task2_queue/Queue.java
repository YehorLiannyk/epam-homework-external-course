package main.ua.advanced.practice2.task2_queue;

import main.ua.advanced.practice2.Container;

public interface Queue extends Container {

    // Appends the specified element to the end.
    void enqueue(Object element);

    // Removes the head.
    Object dequeue();

    // Returns the head.
    Object top();

}