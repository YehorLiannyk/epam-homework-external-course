package main.ua.university.finalTask.bll;

import java.io.Serializable;
import java.util.List;

/*@XmlRootElement(name = "country")
@XmlAccessorType(XmlAccessType.FIELD)*/
public class Country implements Serializable {
    /*@XmlAttribute(name = "id")*/
    private int id;
    /*@XmlElement(name = "citiesArray")*/
    private List<City> cities;
    /*@XmlElement(name = "nameOfCountry")*/
    String countryName;

    public Country(List<City> cities, String countryName) {
        this.cities = cities;
        this.countryName = countryName;
        id = IDHelper.getFreeID();
    }

    public Country(String countryName) {
        this(null, countryName);
    }

    public Country() {
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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
