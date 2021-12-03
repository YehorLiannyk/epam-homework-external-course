package main.ua.university.Task8;

import java.util.Locale;
import java.util.Objects;

public class DataDuplicates {
    DataReader dataReader;
    final private int amountOfDuplicateElemInArr = 3;

    public DataDuplicates(DataReader dataReader) {
        this.dataReader = dataReader;
    }

    public void printFirstDuplicatesInUpCase() {
        String[] data = dataReader.getDataArray();
        String[] duplicates = getFirstDuplicates(data);
        for (var item : duplicates) {
            System.out.println(item.toUpperCase(Locale.ROOT));
        }
    }

    public String[] getFirstDuplicates(String[] data) {
        String[] result = new String[amountOfDuplicateElemInArr];
        int k = 0;
        for (var item : data) {
            int amountOfDuplicate = !Objects.equals(item, "") ? getAmountOfDuplicate(data, item) : 0;
            if (amountOfDuplicate > 1) {
                result[k] = item;
                data = deleteDuplicateInArr(data, item);
                k++;
            }
            if (k == amountOfDuplicateElemInArr)
                break;
        }
        return result;
    }

    public int getAmountOfDuplicate(String[] data, String item) {
        int amountOfDuplicate = 0;
        for (var downItem : data) {
            if (Objects.equals(item, downItem)) {
                amountOfDuplicate++;
            }
        }
        return amountOfDuplicate;
    }

    public String[] deleteDuplicateInArr(String[] data, String word) {
        for (int i = 0; i < data.length; i++)
            if (Objects.equals(data[i], word))
                data[i] = "";
        data = dataReader.clearArrayOutOfNullElement(data);
        return data;
    }
}
