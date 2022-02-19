package main.ua.advanced.practice2.readerAndWriter;

import main.ua.advanced.practice2.City;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static main.ua.advanced.practice2.readerAndWriter.Defaults.*;

public class XMLReader {
    public static List<City> readCitiesFromFile() {
//      XSDValidator.XSDValidate();
        List<City> cities = new ArrayList<>();
        try (XMLDecoder xmlDecoder = new XMLDecoder(new BufferedInputStream(
                new FileInputStream(PATH_DIR + CITIES_XML_FILE)))) {
            cities = (List<City>) xmlDecoder.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return cities;
    }
/*
    public static MyList readMyListCitiesFromFile() {
        String pathDir = "resources" + File.separator + "advanced" + File.separator;
        String citiesFile = "cities_myList.xml";
//        XSDValidator.XSDValidate();
        MyList cities = new MyListImpl<City>();
        try (XMLDecoder xmlDecoder = new XMLDecoder(new BufferedInputStream(
                new FileInputStream(pathDir + citiesFile)))) {
            cities = (MyList) xmlDecoder.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return cities;
    }*/
}
