package main.ua.university.finalTask.bll.country;

import main.ua.university.finalTask.bll.city.City;
import main.ua.university.finalTask.pl.GettingValuesFromInput;

import java.io.Serializable;
import java.util.List;

public class Country implements Serializable {
    String countryName;
    private int id;
    private List<City> cities;

    public Country(List<City> cities, String countryName, int id) {
        this.cities = cities;
        this.countryName = countryName;
        this.id = id;
    }

    public Country(String countryName, int id) {
        this(null, countryName, id);
    }

    public Country(Country oldCountry, int id) {
        this(oldCountry.getCities(), oldCountry.getCountryName(), id);
    }

    public Country() {
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (GettingValuesFromInput.isThisId(id))
            this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }


    public String getCountryAndCitiesInfoInFormat() {
        final StringBuilder sb = new StringBuilder("countryName = '").append(countryName).append("'\n");
        sb.append('\t').append("ID = '").append(id).append('\'');
        try {
            sb.append('\t').append("cities = ").append('\n').append(getCitiesInfoInFormat(cities));
        } catch (NullPointerException e) {
            sb.append('\t').append("there is no city yet").append('\n');
        }
        return sb.toString();
    }

    public String getCountryInfoInFormat() {
        final StringBuilder sb = new StringBuilder("countryName = '").append(countryName).append("'\n");
        sb.append('\t').append("ID = '").append(id).append('\'');
        return sb.toString();
    }

    public String getCitiesInfoInFormat(List<City> cities) {
        final StringBuilder sb = new StringBuilder();
        cities.forEach(city -> sb.append(city.getCityInfoInFormat()).append('\n'));
        return sb.toString();
    }
}
