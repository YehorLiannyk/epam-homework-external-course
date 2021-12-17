package main.ua.university.finalTask.dal;

import main.ua.university.finalTask.bll.country.Country;
import main.ua.university.finalTask.dal.xsdValidation.XSDValidator;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    public static List<Country> readCountriesFromFile() {
        XSDValidator.XSDValidate();
        List<Country> countries = new ArrayList<>();
        try (XMLDecoder xmlDecoder = new XMLDecoder(new BufferedInputStream(
                new FileInputStream(FilePath.pathDir + FilePath.countriesFile)))) {
            countries = (List<Country>) xmlDecoder.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return countries;
    }
}
