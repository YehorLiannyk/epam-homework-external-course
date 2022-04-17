package main.ua.advanced.practice4.task2;

import java.io.IOException;
import java.io.InputStream;

public class Part2 {
    public static void main(String[] args) throws IOException {
        //в мене так і не вийшло реалізувати імітування натискання Enter через нащадка InputStream, хоча й розширив клас
        // і імплементував метод int read(), бо далі в Scanner отримував NullPointerException адже, на жаль, так і не
        // знайшов способу запису в InputStream пустого рядка, як потребує завдання.
        // Тому працює через ручне натискання клавіші.

        //нескінчений цикл, що виводить повідомлення про початок, запускає спам і припиняє його після натискання Enter
        // а далі виводить повідомлення про кінець. І все починається спочатку
        while (true) {
            startMsg();

            final InputStream CACHED_VALUE_OF_SYSTEM_IN = System.in;
            final InputStream MY_OWN_INPUT_STREAM = new MyInputStream();

            System.setIn(CACHED_VALUE_OF_SYSTEM_IN); //MY_OWN_INPUT_STREAM has to be here


            Thread t = new Thread() { public void run() { Spam.main(null); } };
            t.start();

            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.setIn(CACHED_VALUE_OF_SYSTEM_IN);
            endMsg();
        }
    }

    private static void startMsg() {
        System.out.println("Start spamming");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void endMsg() {
        System.out.println("Spamming is finished");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyInputStream extends java.io.InputStream {
    @Override
    public int read() throws IOException {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
