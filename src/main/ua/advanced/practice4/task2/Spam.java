package main.ua.advanced.practice4.task2;

import java.util.Objects;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Spam {
    private Thread[] threads;
    private Timer timer = new Timer();

    private String[] messages;
    private int[] intervals;
    private int size;

    public Spam(String[] messages, int[] intervals) {
        if(messages.length < 2 || intervals.length < 2 || messages.length != intervals.length)
            throw new IllegalArgumentException();

        this.messages = messages;
        this.intervals = intervals;
        size = messages.length;
        threads = new Thread[size];

        setThreads(messages, intervals);
    }

    private void setThreads(String[] messages, int[] intervals) {
        for (int i = 0; i < size; i++) {
            threads[i] = new Worker(messages[i], intervals[i] );
        }
    }

    public void start() {
        for (int i = 0; i < size; i++) {
            threads[i].start();
        }
    }

    public void stop() {
        for (int i = 0; i < size; i++) {
            //threads[]
            timer.cancel();
        }
    }

    private class Worker extends Thread {
        private String msg;
        private int interval;

        public Worker(String msg, int interval) {
            this.msg = msg;
            this.interval = interval;
        }

        @Override
        public void run() {
            TimerTask timerTask = new TimerTask (){
                private static int i;
                @Override
                public void run() {
                    System.out.println(msg);
                }
            };
            timer.schedule(timerTask, 10, interval);
        }
    }
    public static void main(String[] args) {
        String[] messages = new String[] { "@@@", "bbbbbbb" };
        int[] times = new int[] { 333, 222 };
        Spam spam = new Spam(messages, times);
        spam.start();

        System.out.println("Press Enter to stop");
        Scanner sc = new Scanner(System.in);
        String value = sc.nextLine();
        if (Objects.equals(value, "")) {
            spam.stop();
        }
    }
}