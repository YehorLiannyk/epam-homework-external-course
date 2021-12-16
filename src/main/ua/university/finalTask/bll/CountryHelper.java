package main.ua.university.finalTask.bll;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

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

    private void cityExistingCheck(Country country, String nameCity) {
        if (!isThisCityExist(country, nameCity))
            throw new NoSuchElementException();
    }

    private void cityExistingCheck(Country country, int idCity) {
        if (!isThisCityExist(country, idCity))
            throw new NoSuchElementException();
    }

    private void cityExistingCheck(List<Country> countries, String nameCity) {
        boolean exist = false;
        for (var country : countries)
            if (isThisCityExist(country, nameCity)) {
                exist = true;
                break;
            }
        if (!exist)
            throw new NoSuchElementException();
    }

    private void cityExistingCheck(List<Country> countries, int idCity) {
        boolean exist = false;
        for (var country : countries)
            if (isThisCityExist(country, idCity)) {
                exist = true;
                break;
            }
        if (!exist)
            throw new NoSuchElementException();
    }

    private void cityExistingCheckInCountry(Country country, City city) {
        boolean exist = isThisCityExistInCountry(country, city);
        if (!exist)
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

    public City findNeededCity(Country country, String nameCity) {
        cityExistingCheck(country, nameCity);
        for (var city : country.getCities()) {
            if (Objects.equals(city.getCityName(), nameCity))
                return city;
        }
        return null;
    }

    public City findNeededCity(Country country, int idCity) {
        cityExistingCheck(country, idCity);
        for (var city : country.getCities()) {
            if (Objects.equals(city.getId(), idCity))
                return city;
        }
        return null;
    }

    public City findNeededCity(List<Country> countries, String nameCity) {
        cityExistingCheck(countries, nameCity);
        for (var country : countries)
            for (var city : country.getCities()) {
                if (Objects.equals(city.getCityName(), nameCity))
                    return city;
            }
        return null;
    }

    public City findNeededCity(List<Country> countries, int idCity) {
        cityExistingCheck(countries, idCity);
        for (var country : countries)
            for (var city : country.getCities()) {
                if (Objects.equals(city.getId(), idCity))
                    return city;
            }
        return null;
    }

    public boolean isThisCityExist(Country country, String nameCity) {
        boolean check = false;
        for (var city : country.getCities())
            if (Objects.equals(nameCity, city.getCityName())) {
                check = true;
                break;
            }
        return check;
    }

    public boolean isThisCityExist(List<Country> countries, String nameCity) {
        boolean check = false;
        for (var country : countries)
            for (var city : country.getCities())
                if (Objects.equals(nameCity, city.getCityName())) {
                    check = true;
                    break;
                }
        return check;
    }

    public boolean isThisCityExist(Country country, int idCity) {
        boolean check = false;
        for (var city : country.getCities())
            if (Objects.equals(idCity, city.getId())) {
                check = true;
                break;
            }
        return check;
    }

    public boolean isThisCityExist(List<Country> countries, int idCity) {
        boolean check = false;
        for (var country : countries)
            for (var city : country.getCities())
                if (Objects.equals(idCity, city.getId())) {
                    check = true;
                    break;
                }
        return check;
    }

    public boolean isThisCityExistInCountry(Country country, City city) {
        boolean check = false;
        for (var cityItem : country.getCities())
            if (Objects.equals(cityItem, city)) {
                check = true;
                break;
            }
        return check;
    }

    public boolean doesCountryHaveCapital(Country country) {
        AtomicBoolean check = new AtomicBoolean(false);
        country.getCities().forEach(city -> {
            if (city.isCapital()) {
                check.set(true);
            }
        });
        return check.get();
    }

    public List<Country> updateCountryInCountryList(List<Country> list, Country oldCountry, Country newCountry) {
        int index = list.indexOf(oldCountry);
        list.set(index, newCountry);
        return list;
    }

    public List<Country> deleteCountry(List<Country> countries, Country country) {
        try {
            countries.remove(country);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countries;
    }

    public List<Country> deleteCity(List<Country> countries, City city) {
        Country countryBeforeDelete = findNeededCountryByCity(countries, city);
        Country countryAfterDelete = countryBeforeDelete;

        cityExistingCheckInCountry(countryBeforeDelete, city);

        List<City> cityList = countryBeforeDelete.getCities();
        cityList.remove(city);
        countryAfterDelete.setCities(cityList);
        countries = updateCountryInCountryList(countries, countryBeforeDelete, countryAfterDelete);
        return countries;
    }

    public Country addNewCity(Country country, City newCity) {
        List<City> list = country.getCities();
        list.add(newCity);
        country.setCities(list);
        return country;
    }
}
