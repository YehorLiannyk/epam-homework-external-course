package main.ua.university.Task8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class DataReader {
    private Scanner file;
    private String text;

    public DataReader() {
        try {
            file = new Scanner(new File("text.csv"));
        } catch (FileNotFoundException e) {
            System.out.println("The file doesn't exist");
        }
        text  = getFileText();
    }

    public DataReader(String text) {
        this.text = text;
    }

    String getFileText() {
        String str = "";
        try {
            str = file.nextLine();
        } catch (NoSuchElementException e) {
            System.out.println("File is empty");
        }
        return str;
    }

    public String[] getDataArray() {
        String str = text;
        return str.split(";");
    }

/*    public String[] getUniqueDataArray() {
        String[] str = getDataArray();
        String[] equal = str.clone();
        String[] temp = new String[10];
        int k = 0;

        for (int i = 0; i < str.length; i++) {
            for (int j = 0; j < str.length; j++) {
                if (!Objects.equals(equal[j], ""))
                    if (Objects.equals(str[i], equal[j])) {
                        temp[k] = str[i];
                        equal[j] = "";
                    }
            }
            k++;
        }
        return clearArrayOutOfNullElement(temp);
    }*/

    public String[] clearArrayOutOfNullElement(String[] arr) {
        int amountOfNull = 0;
        for (var item : arr)
            if (item == null || item.equals(""))
                amountOfNull++;
        String[] newArr = new String[arr.length - amountOfNull];
        int k = 0;
        for (var item : arr)
            if (item != null && !item.equals("")) {
                newArr[k] = item;
                k++;
            }
        return newArr;
    }
}
