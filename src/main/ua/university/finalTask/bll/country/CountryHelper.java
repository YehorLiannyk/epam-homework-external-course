package main.ua.university.finalTask.bll.country;

import main.ua.university.finalTask.bll.city.City;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class CountryHelper {

    public Country findNeededCountry(List<Country> countries, String nameCountry) {
        countryExistingCheck(countries, nameCountry);
        for (var country : countries) {
            if (Objects.equals(country.getCountryName(), nameCountry))
                return country;
        }
        return null;
    }

    public Country findNeededCountryByCity(List<Country> countries, City city) {
        for (var country : countries) {
            for (var cityItem : country.getCities())
                if (Objects.equals(cityItem, city))
                    return country;
        }
        return null;
    }

    public Country findNeededCountry(List<Country> countries, int idCountry) {
        countryExistingCheck(countries, idCountry);
        for (var country : countries) {
            if (Objects.equals(country.getId(), idCountry))
                return country;
        }
        return null;
    }

    private void countryExistingCheck(List<Country> countries, String nameCountry) {
        if (!isThisCountryExist(countries, nameCountry))
            throw new NoSuchElementException();
    }

    private void countryExistingCheck(List<Country> countries, int idCountry) {
        if (!isThisCountryExist(countries, idCountry))
            throw new NoSuchElementException();
    }

    public boolean isThisCountryExist(List<Country> countries, String nameCountry) {
        boolean check = false;
        for (var country : countries)
            if (Objects.equals(nameCountry, country.getCountryName())) {
                check = true;
                break;
            }
        return check;
    }

    public boolean isThisCountryExist(List<Country> countries, int idCountry) {
        boolean check = false;
        for (var country : countries)
            if (Objects.equals(idCountry, country.getId())) {
                check = true;
                break;
            }
        return check;
    }

    public boolean doesCountryHaveCapital(Country country) {
        AtomicBoolean check = new AtomicBoolean(false);
        if (country.getCities() != null) {
            country.getCities().forEach(city -> {
                if (city.isCapital()) {
                    check.set(true);
                }
            });
        }
        return check.get();
    }

    public List<Country> updateCountryInCountryList(List<Country> list, Country oldCountry, Country newCountry) {
        AtomicInteger index = new AtomicInteger(-1);
        list.forEach(country -> {
            if (country.getId() == oldCountry.getId()) {
                index.set(list.indexOf(country));
            }
        });
        list.set(index.get(), newCountry);
        return list;
    }

    public List<Country> addNewCountryToCountryList(List<Country> countries, Country country) {
        if (countries == null)
            countries = new ArrayList<>();
        countries.add(country);
        return countries;
    }

    public List<Country> deleteCountry(List<Country> countries, Country country) {
        try {
            countries.remove(country);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countries;
    }

    public Country createNewCountryByName(String name, int id) {
        return new Country(name, id);
    }

    public Country getCountryAfterChangingName(Country country, String newName, int id) {
        Country newCountry = new Country(country, id);
        newCountry.setCountryName(newName);
        return newCountry;
    }
}
