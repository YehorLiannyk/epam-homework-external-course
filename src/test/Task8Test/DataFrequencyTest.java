package Task8Test;

import main.ua.university.Task8.DataReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.ua.university.Task8.DataFrequency;

import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

class DataFrequencyTest {
    String[] fileStringArray;
    DataFrequency dataFrequency;

    @BeforeEach
    void preparingFrequency() {
        fileStringArray = DefaultArrays.array.clone();
        String data = DefaultArrays.data;
        dataFrequency = new DataFrequency(new DataReader(data));
    }

    @Test
    void getTopThreeFrequencies() {
        LinkedHashMap<String, Integer> givenMap = dataFrequency.getTopThreeFrequencies(fileStringArray);
        LinkedHashMap<String, Integer> test = new LinkedHashMap();
        test.put("arn", 5);
        test.put("praga", 4);
        test.put("mama", 2);

        Assertions.assertTrue(test.equals(givenMap));

        test.put("test", 4);
        Assertions.assertFalse(test.equals(givenMap));
    }

    @Test
    void sortAllFrequencies() {
        LinkedHashMap<String, Integer> givenMap = dataFrequency.sortAllFrequencies(fileStringArray);
        LinkedHashMap<String, Integer> test = new LinkedHashMap();
        test.put("arn", 5);
        test.put("praga", 4);
        test.put("mama", 2);
        test.put("mia", 2);

        Assertions.assertTrue(test.equals(givenMap));

        test = new LinkedHashMap();
        test.put("arn", 5);
        test.put("mama", 2);
        test.put("praga", 4);
        test.put("mia", 2);
        AtomicBoolean check = checkEqualityByOrder(givenMap, test);
        Assertions.assertFalse(check.get());
    }

    private AtomicBoolean checkEqualityByOrder(LinkedHashMap<String, Integer> givenMap, LinkedHashMap<String, Integer> test) {
        AtomicBoolean check = new AtomicBoolean(true);
        test.forEach((k, v) ->
                givenMap.forEach((gK, gV) -> {
                    if (!Objects.equals(gK, k) || !Objects.equals(gV, v)) {
                        check.set(false);
                    }
                }));
        return check;
    }

    @Test
    void findAllFrequencies() {
        LinkedHashMap<String, Integer> givenMap = dataFrequency.findAllFrequencies(fileStringArray);
        LinkedHashMap<String, Integer> test = new LinkedHashMap();
        test.put("mama", 2);
        test.put("mia", 2);
        test.put("arn", 5);
        test.put("praga", 4);

        Assertions.assertTrue(test.equals(givenMap));

        test.put("test", 4);
        Assertions.assertFalse(test.equals(givenMap));
    }
}