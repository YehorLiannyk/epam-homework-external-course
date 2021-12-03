package Task8Test;

import main.ua.university.Task8.DataReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataReaderTest {

    DataReader dataReader;
    String[] fileStringArray;

    @BeforeEach
    void setDataReader() {
        dataReader = new DataReader("");
        fileStringArray = DefaultArrays.array.clone();
    }

    @Test
    void clearArrayOutOfNullElement() {
        String[] realArr = dataReader.clearArrayOutOfNullElement(DefaultArrays.arrayWithoutMama);
        String[] rightArr = DefaultArrays.arrayWithoutMamaAndNull;
        Assertions.assertArrayEquals(rightArr, realArr);

    }
}