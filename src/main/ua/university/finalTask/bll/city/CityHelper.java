package main.ua.university.finalTask.bll.city;

import main.ua.university.finalTask.bll.country.Country;
import main.ua.university.finalTask.bll.country.CountryHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class CityHelper {
    CountryHelper countryHelper;

    public CityHelper(CountryHelper countryHelper) {
        this.countryHelper = countryHelper;
    }

    public List<Country> updateCityInCountryList(List<Country> countries, City oldCity, City newCity) {
        Country countryBeforeDelete = oldCity.getCountry();
        Country countryAfterDelete = countryBeforeDelete;

        cityExistingCheckInCountry(countryBeforeDelete, oldCity);

        List<City> cityList = countryBeforeDelete.getCities();
        int index = cityList.indexOf(oldCity);
        cityList.set(index, newCity);
        countryAfterDelete.setCities(cityList);
        countries = countryHelper.updateCountryInCountryList(countries, countryBeforeDelete, countryAfterDelete);
        return countries;
    }

    public City findNeededCity(Country country, String nameCity) {
        cityExistingCheck(country, nameCity);
        if (country.getCities() != null)
            for (var city : country.getCities()) {
                if (Objects.equals(city.getCityName(), nameCity))
                    return city;
            }
        return null;
    }

    public City findNeededCity(Country country, int idCity) {
        cityExistingCheck(country, idCity);
        if (country.getCities() != null)
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

    public List<Country> deleteCity(List<Country> countries, City city) {
        Country countryBeforeDelete = countryHelper.findNeededCountryByCity(countries, city);
        Country countryAfterDelete = countryBeforeDelete;

        cityExistingCheckInCountry(countryBeforeDelete, city);

        List<City> cityList = countryBeforeDelete.getCities();
        cityList.remove(city);
        countryAfterDelete.setCities(cityList);
        countries = countryHelper.updateCountryInCountryList(countries, countryBeforeDelete, countryAfterDelete);
        return countries;
    }

    public Country addNewCity(Country country, City newCity) {
        List<City> list = country.getCities() != null ? country.getCities() : new ArrayList<>();
        list.add(newCity);
        country.setCities(list);
        return country;
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

    public boolean isThisCityExist(Country country, String nameCity) {
        boolean check = false;
        if (country.getCities() != null) {
            for (var city : country.getCities())
                if (Objects.equals(nameCity, city.getCityName())) {
                    check = true;
                    break;
                }
        }
        return check;
    }

    public boolean isThisCityExist(List<Country> countries, String nameCity) {
        boolean check = false;
        for (var country : countries)
            if (country.getCities() != null) {
                for (var city : country.getCities())
                    if (Objects.equals(nameCity, city.getCityName())) {
                        check = true;
                        break;
                    }
            }
        return check;
    }

    public boolean isThisCityExist(Country country, int idCity) {
        boolean check = false;
        if (country.getCities() != null)
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
            if (country.getCities() != null)
                for (var city : country.getCities())
                    if (Objects.equals(idCity, city.getId())) {
                        check = true;
                        break;
                    }
        return check;
    }

    public boolean isThisCityExistInCountry(Country country, City city) {
        boolean check = false;
        if (country.getCities() != null)
            for (var cityItem : country.getCities())
                if (Objects.equals(cityItem, city)) {
                    check = true;
                    break;
                }
        return check;
    }

    public City getCityAfterChangingName(City city, String newName, int id) {
        City newCity = new City(city, id);
        newCity.setCityName(newName);
        return newCity;
    }

    public City getCityAfterChangingPopulation(City city, int population, int id) {
        City newCity = new City(city, id);
        newCity.setPopulation(population);
        return newCity;
    }

    public City getCityAfterChangingCapitalStatus(City city, boolean isCapital, int id) {
        City newCity = new City(city, id);
        newCity.setCapital(isCapital);
        return newCity;
    }

    public City createNewCity(Country country, String cityName, int population, boolean isCapital, int id) {
        return new City(country, cityName, population, isCapital, id);
    }
}
