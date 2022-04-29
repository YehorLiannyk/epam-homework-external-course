package main.ua.advanced.practice6.iterator;

import main.ua.advanced.practice6.iterator.tableIterator.TableIterator;
import main.ua.advanced.practice6.iterator.timesIterators.FiveTimesTimesIterator;
import main.ua.advanced.practice6.iterator.timesIterators.ThreeTimesTimesIterator;
import main.ua.advanced.practice6.iterator.timesIterators.TwoTimesTimesIterator;

import java.io.IOException;
import java.util.Iterator;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Iterators {
    public static final Logger logger = Logger.getLogger(Iterators.class.getName());

    //just for demonstration
    static {
        try {
            Handler fileHandler = new FileHandler("resources/advanced/practice6/" + "iterator.log");
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Iterator<Integer> intArrayTwoTimesIterator(int[] array) {
        return new TwoTimesTimesIterator(array);
    }

    public static Iterator<Integer> intArrayThreeTimesIterator(int[] array) {
        return new ThreeTimesTimesIterator(array);
    }

    public static Iterator<Integer> intArrayFiveTimesIterator(int[] array) {
        return new FiveTimesTimesIterator(array);

    }

    public static Iterable<String> table(String[] columns, int[] rows) {
        return new TableIterator(columns, rows);
    }


}
