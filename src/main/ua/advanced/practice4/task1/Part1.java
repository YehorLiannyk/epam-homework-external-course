package main.ua.advanced.practice4.task1;

import java.lang.Thread;

class MyThread extends Thread {
    @Override
    public void run() {
        int millis = 0;
        while (millis < Part1.MILLIS_FOR_THREAD) {
            try {
                System.out.println("My name is" + getName());
                sleep(Part1.MILLIS_FOR_NAME);
                millis += Part1.MILLIS_FOR_NAME;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        int millis = 0;
        while (millis < Part1.MILLIS_FOR_THREAD) {
            try {
                System.out.println("My name is" + Thread.currentThread().getName());
                Thread.sleep(Part1.MILLIS_FOR_NAME);
                millis += Part1.MILLIS_FOR_NAME;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Part1 {
    public static final int MILLIS_FOR_NAME = 333;
    public static final int MILLIS_FOR_THREAD = 2000;

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread myRunnable = new Thread(new MyRunnable());
        myThread.start();

        try {
            myThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myRunnable.start();
    }
}
