package main.ua.university.finalTask.dal;

import main.ua.university.finalTask.bll.Country;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class DataReader {
    /*public static List<Country> readCountryFromFile() {
        List<Country> countries = new ArrayList<>();
        try (XMLDecoder xmlDecoder = new XMLDecoder(new BufferedInputStream(
                new FileInputStream(FilePath.pathDir + "product.xml")))) {
            countries.add((Country) xmlDecoder.readObject());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return countries;
    }*/

    public static List<Country> readCountriesFromFile()  {
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
    }
}
