package main.ua.university.finalTask.bll;

import main.ua.university.finalTask.bll.country.Country;
import main.ua.university.finalTask.dal.DataReader;
import main.ua.university.finalTask.dal.DataWriter;

import java.io.FileNotFoundException;
import java.util.List;

public class DALConnector {
    List<Country> countries;

    public DALConnector() {
        countries = DataReader.readCountriesFromFile();
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public void writeToFile(List<Country> list) throws FileNotFoundException {
        DataWriter.writeCountriesToFile(list);
    }
}
