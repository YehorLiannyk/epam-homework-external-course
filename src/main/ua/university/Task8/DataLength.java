package main.ua.university.Task8;

import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class DataLength {
    DataReader dataReader;

    public DataLength(DataReader dataReader) {
        this.dataReader = dataReader;
    }

    void printTopThreeDataLength() {
        LinkedHashMap<String, Integer> words = getTopThreeDataLength(dataReader.getDataArray());
        words.forEach((k, v) -> System.out.println(k + " ==> " + v));
    }

    public LinkedHashMap<String, Integer> getTopThreeDataLength(String[] data) {
        final int topNumber = 3;
        LinkedHashMap<String, Integer> all = sortAllDataByLength(data);
        LinkedHashMap<String, Integer> top = new LinkedHashMap<>();
        all.forEach((k, v) -> {
            if (top.size() < 3)
                top.put(k, v);
        });
        return top;
    }

    public LinkedHashMap<String, Integer> getAllDataLength(String[] data) {
        return sortAllDataByLength(data);
    }

    public LinkedHashMap<String, Integer> sortAllDataByLength(String[] data) {
        LinkedHashMap<String, Integer> words = findAllDataLength(data);
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

    public LinkedHashMap<String, Integer> findAllDataLength(String[] data) {
        LinkedHashMap<String, Integer> words = new LinkedHashMap<>();
        for (String word : data) {
            int length = word.length();
            words.put(word, length);
        }
        return words;
    }


}
