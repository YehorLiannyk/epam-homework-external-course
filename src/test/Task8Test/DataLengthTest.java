package Task8Test;

import main.ua.university.Task8.DataLength;
import main.ua.university.Task8.DataReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

class DataLengthTest {

    String[] fileStringArray;
    DataLength dataLength;

    @BeforeEach
    void preparingDataLength() {
        fileStringArray = DefaultArrays.array.clone();
        String data = DefaultArrays.data;
        dataLength = new DataLength(new DataReader(data));
    }

    @Test
    void getTopThreeDataLength() {
        LinkedHashMap<String, Integer> givenMap = dataLength.getTopThreeDataLength(fileStringArray);
        LinkedHashMap<String, Integer> test = new LinkedHashMap();
        test.put("praga", 5);
        test.put("mama", 4);
        test.put("mia", 3);

        Assertions.assertTrue(test.equals(givenMap));

        test = new LinkedHashMap();
        test.put("mama", 4);
        test.put("mia", 3);
        test.put("arn", 3);
        test.put("praga", 5);
        AtomicBoolean check = checkEqualityByOrder(givenMap, test);
        Assertions.assertFalse(check.get());
    }

    @Test
    void sortAllDataByLength() {
        LinkedHashMap<String, Integer> givenMap = dataLength.sortAllDataByLength(fileStringArray);
        LinkedHashMap<String, Integer> test = new LinkedHashMap();
        test.put("praga", 5);
        test.put("mama", 4);
        test.put("mia", 3);
        test.put("arn", 3);

        Assertions.assertTrue(test.equals(givenMap));

        test = new LinkedHashMap();
        test.put("mama", 4);
        test.put("mia", 3);
        test.put("arn", 3);
        test.put("praga", 5);
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
    void findAllDataLength() {
        LinkedHashMap<String, Integer> givenMap = dataLength.sortAllDataByLength(fileStringArray);
        LinkedHashMap<String, Integer> test = new LinkedHashMap();
        test.put("mama", 4);
        test.put("mia", 3);
        test.put("arn", 3);
        test.put("praga", 5);

        Assertions.assertTrue(test.equals(givenMap));

        test.put("test", 4);
        Assertions.assertFalse(test.equals(givenMap));
    }
}