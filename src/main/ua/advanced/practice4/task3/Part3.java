package main.ua.advanced.practice4.task3;

import java.util.concurrent.TimeUnit;

public class Part3 {
    private int fCounter;
    private int sCounter;

    public static void main(String[] args) {
        System.out.println("Non synchronized");
        new Part3().callNonSyncThreads();

        pauseForOneSecond();

        System.out.println("Synchronized");
        new Part3().callSyncThreads();
    }

    private static void pauseForOneSecond() {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void callNonSyncThreads() {
        Thread fThread = new Thread(new MyRunnable(), "Thread #1n");
        Thread sThread = new Thread(new MyRunnable(), "Thread #2n");
        Thread tThread = new Thread(new MyRunnable(), "Thread #3n");

        fThread.start();
        sThread.start();
        tThread.start();
    }

    public void callSyncThreads() {
        Thread fThread = new Thread(new MySyncRunnable(), "Thread #1s");
        Thread sThread = new Thread(new MySyncRunnable(), "Thread #2s");
        Thread tThread = new Thread(new MySyncRunnable(), "Thread #3s");

        fThread.start();
        sThread.start();
        tThread.start();
    }

    private String getCompareString() {
        int compareRes = Integer.compare(fCounter, sCounter);
        String compareString;
        if (compareRes == 1)
            compareString = "firstCounter(" + fCounter + ") > secondCounter(" + sCounter + ")";
        else if (compareRes == -1)
            compareString = "firstCounter(" + fCounter + ") < secondCounter(" + sCounter + ")";
        else
            compareString = "firstCounter(" + fCounter + ") = secondCounter(" + sCounter + ")";
        return compareString;
    }

    private void compare() {
        String compareString = getCompareString();
        System.out.println(Thread.currentThread().getName() + ": " + compareString);
        fCounter++;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sCounter++;
    }

    private synchronized void syncCompare() {
        String compareString = getCompareString();
        System.out.println(Thread.currentThread().getName() + ": " + compareString);
        fCounter++;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sCounter++;
    }

    public class MyRunnable implements Runnable {
        private int stepsAmount = 5;

        @Override
        public void run() {
            while (stepsAmount > 0) {
                compare();
                stepsAmount--;
            }
        }
    }

    public class MySyncRunnable implements Runnable {
        private int stepsAmount = 5;

        @Override
        public synchronized void run() {
            while (stepsAmount > 0) {
                syncCompare();
                stepsAmount--;
            }
        }
    }
}
