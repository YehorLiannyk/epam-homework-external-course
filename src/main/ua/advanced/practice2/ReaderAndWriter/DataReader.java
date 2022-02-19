package main.ua.advanced.practice2.ReaderAndWriter;

import main.ua.advanced.practice2.CityAndCountry.City;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    public static List<City> readCitiesFromFile() {
        String pathDir = "resources" + File.separator;
        String citiesFile = "cities.xml";
//        XSDValidator.XSDValidate();
        List<City> cities = new ArrayList<>();
        try (XMLDecoder xmlDecoder = new XMLDecoder(new BufferedInputStream(
                new FileInputStream(pathDir + citiesFile)))) {
            cities = (List<City>) xmlDecoder.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return cities;
    }
}
