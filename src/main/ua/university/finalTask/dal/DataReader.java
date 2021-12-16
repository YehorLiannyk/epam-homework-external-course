package main.ua.university.finalTask.dal;

import main.ua.university.finalTask.bll.Country;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    public static List<Country> readCountriesFromFile() {
        List<Country> countries = new ArrayList<>();
        try (XMLDecoder xmlDecoder = new XMLDecoder(new BufferedInputStream(
                new FileInputStream(FilePath.pathDir + FilePath.countriesFile)))) {
            countries = (List<Country>) xmlDecoder.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return countries;
    }

/*    public static List<Country> readCountriesFromFile()  {
        XMLDecoder in = null;
        try {
            in = new XMLDecoder( new BufferedInputStream( new ObjectInputStream(
                    new FileInputStream(FilePath.pathDir + "country.xml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Country> countries = (List<Country>)in.readObject();
        in.close();
        return countries;

    }*/
}
