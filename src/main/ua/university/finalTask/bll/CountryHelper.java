package main.ua.university.finalTask.bll;

import main.ua.university.finalTask.dal.DataHelper;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class CountryHelper {
    Country country;

    public CountryHelper(Country country) {
        this.country = country;
    }

    public static Country findNeededCountry(String nameCountry) {
        countryExistingCheck(nameCountry);

        return new Country(); /////////////
    }

    public static Country findNeededCountry(int idCountry) {
        countryExistingCheck(idCountry);

        return new Country(); /////////////
    }

    private static void countryExistingCheck(String nameCountry) {
        if (!isThisCountryExist(nameCountry))
            throw new NoSuchElementException();
    }

    private static void countryExistingCheck(int idCountry) {
        if (!isThisCountryExist(idCountry))
            throw new NoSuchElementException();
    }

    public static boolean isThisCountryExist(String nameCountry) {
        List<Country> countries = DataHelper.getAllCountryFromFile();
        boolean check = false;
        for (var country : countries)
            if (Objects.equals(nameCountry, country.getNameOfCounty())) {
                check = true;
                break;
            }
        return check;
    }

    public static boolean isThisCountryExist(int idCountry) {
        List<Country> countries = DataHelper.getAllCountryFromFile();
        boolean check = false;
        for (var country : countries)
            if (Objects.equals(idCountry, country.getNameOfCounty())) {
                check = true;
                break;
            }
        return check;
    }



}
