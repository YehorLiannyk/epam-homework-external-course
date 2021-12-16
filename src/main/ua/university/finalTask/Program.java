package main.ua.university.finalTask;

import main.ua.university.finalTask.bll.City;
import main.ua.university.finalTask.bll.Country;
import main.ua.university.finalTask.dal.DataReader;
import main.ua.university.finalTask.pl.menu.Menu;

import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        //List<Country> list = createTestObj();

        Menu menu = new Menu();
        menu.startProgram();

    }

    private static List<Country>  createTestObj() {
       Country ukraine = new Country("Ukraine");
        List<City> citiesUK = new ArrayList<>();
        citiesUK.add(new City(ukraine, "Kyiv", 3_200_000, true));
        citiesUK.add(new City(ukraine, "Dnipro", 1_000_000));
        citiesUK.add(new City(ukraine, "Odesa", 1_500_000));
        ukraine.setCities(citiesUK);

        Country poland = new Country("Poland");
        List<City> citiesPL = new ArrayList<>();
        citiesPL.add(new City(poland, "Warsaw", 3_200_000, true));
        citiesPL.add(new City(poland, "Lyublin", 1_000_000));
        poland.setCities(citiesPL);

        List<Country> countries = new ArrayList<>();
        countries.add(ukraine);
        countries.add(poland);
        //DataWriter.writeCountriesToFile(countries);

        List<Country> test = DataReader.readCountriesFromFile();
        return test;
    }
}
