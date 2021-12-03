package main.ua.university.Task8;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class DataFrequency {
    DataReader dataReader;

    public DataFrequency(DataReader dataReader) {
        this.dataReader = dataReader;
    }

    void printTopThreeFrequencies() {
        LinkedHashMap<String, Integer> words = getTopThreeFrequencies(dataReader.getDataArray());
        words.forEach((k, v) -> System.out.println(k + " ==> " + v));
    }

    public LinkedHashMap<String, Integer> getTopThreeFrequencies(String[] data) {
        final int topNumber = 3;
        LinkedHashMap<String, Integer> all = sortAllFrequencies(data);
        LinkedHashMap<String, Integer> top = new LinkedHashMap<>();
        all.forEach((k, v) -> {
            if (top.size() < 3)
                top.put(k, v);
        });
        return top;
    }


    public LinkedHashMap<String, Integer> getAllFrequencies(String[] data) {
        return sortAllFrequencies(data);
    }

    public LinkedHashMap<String, Integer> sortAllFrequencies(String[] data) {
        LinkedHashMap<String, Integer> words = findAllFrequencies(data);
        LinkedHashMap<String, Integer> result = new LinkedHashMap<>();

        int size = words.size();
        for (int i = 0; i < size; i++) {
            AtomicInteger tempValue = new AtomicInteger();
            AtomicReference<String> tempKey = new AtomicReference<>();

            words.forEach((k, v) -> {
                if (tempValue.get() < v) {
                    tempKey.set(k);
                    tempValue.set(v);
                }
            });

            result.put(tempKey.toString(), tempValue.intValue());
            words.remove(tempKey.toString(), tempValue.intValue());
        }

        return result;
    }

    public LinkedHashMap<String, Integer> findAllFrequencies(String[] data) {
        LinkedHashMap<String, Integer> wordFreq = new LinkedHashMap<>();
        for (String word : data) {
            int frequency = 0;
            for (String datum : data)
                if (Objects.equals(word, datum))
                    frequency++;

            if (!wordFreq.containsKey(word))
                wordFreq.put(word, frequency);
        }
        return wordFreq;
    }
}
