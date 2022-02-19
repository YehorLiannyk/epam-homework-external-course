package main.ua.advanced.practice2;

import java.io.Serializable;

public class City implements Serializable {
    private int id;
    private String country;
    private String cityName;
    private int population;
    private boolean isCapital;

    public City(String country, String cityName, int population, boolean isCapital, int id) {
        this.country = country;
        this.cityName = cityName;
        this.population = population;
        this.isCapital = isCapital;
        this.id = id;
    }

    public City() {
    }

    public City(String country, String cityName, int population, int id) {
        this(country, cityName, population, false, id);
    }

    public City(City oldCity, int id) {
        this(oldCity.getCountry(), oldCity.getCityName(), oldCity.getPopulation(), id);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        //if (GettingValuesFromInput.isThisId(id))
            this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public boolean isCapital() {
        return isCapital;
    }

    public void setCapital(boolean capital) {
        isCapital = capital;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder().append("cityName = '").append(cityName).append("'");
        sb.append('\t').append("ID = '").append(id).append('\'');
        if (isCapital)
            sb.append('\t').append("capital of ").append(country);
        else
            sb.append('\t').append("country = '").append(country).append('\'');
        sb.append('\t').append("peopleAmount = '").append(population).append("'");
        return sb.toString();
    }

}