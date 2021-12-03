package Task8Test;

import main.ua.university.Task8.DataDuplicates;
import main.ua.university.Task8.DataReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class DataDuplicatesTest {

    String[] fileStringArray;
    DataDuplicates dataDuplicates;

    @BeforeEach
    void prepareDataDuplicate() {
        fileStringArray = DefaultArrays.array.clone();
        String data = DefaultArrays.data;
        dataDuplicates = new DataDuplicates(new DataReader(data));
    }

    @Test
    void getFirstDuplicates() {
        String[] realArr = dataDuplicates.getFirstDuplicates(fileStringArray);

        String[] rightArr = new String[] {"mama", "mia", "arn"};
        Assertions.assertArrayEquals(rightArr, realArr);

        String[] wrongArr = new String[] {"mama", "arn", "mia"};
        Assertions.assertFalse(Arrays.equals(wrongArr, realArr));
    }

    @Test
    void getAmountOfDuplicate() {
        String item = "mama";
        int rightAmountOfDuplicate = 2;
        int realAmountOfDuplicate = dataDuplicates.getAmountOfDuplicate(fileStringArray, item);
        Assertions.assertEquals(rightAmountOfDuplicate, realAmountOfDuplicate);
    }

    @Test
    void deleteDuplicateInArr() {
        String item = "mama";
        String[] withoutDuplic = dataDuplicates.deleteDuplicateInArr(fileStringArray, item);
        /*String[] rightArr = new String[] {
                "", "mia", "", "mia", "arn", "arn", "arn", "arn", "arn", "praga", "praga", "praga", "praga"
        };*/

        String[] rightArr = new String[] {
                "mia", "mia", "arn", "arn", "arn", "arn", "arn", "praga", "praga", "praga", "praga"
        };

        Assertions.assertArrayEquals(rightArr, withoutDuplic);

        String[] wrongArr = new String[] {
                "mama", "mia", "mama", "mia", "arn", "arn", "arn", "arn", "arn", "praga", "praga", "praga", "praga"
        };
        Assertions.assertFalse(Arrays.equals(wrongArr, withoutDuplic));
    }
}