package main.ua.university.finalTask.dal;

import main.ua.university.finalTask.bll.country.Country;

import java.beans.XMLEncoder;
import java.io.*;
import java.util.List;

public class DataWriter {
    public static void writeCountriesToFile(List<Country> countries) throws FileNotFoundException {
        try (XMLEncoder xmlEncoder = new XMLEncoder(new BufferedOutputStream(
                new FileOutputStream(FilePath.pathDir + FilePath.countriesFile)))) {
            xmlEncoder.writeObject(countries);
            xmlEncoder.flush();
        }
    }
}
