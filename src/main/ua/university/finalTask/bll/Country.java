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
    String nameOfCounty;

    public Country(List<City> cities, String nameOfCounty) {
        this.cities = cities;
        this.nameOfCounty = nameOfCounty;
        id = IDHelper.getFreeID();
    }

    public Country(String nameOfCounty) {
        this(null, nameOfCounty);
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

    public String getNameOfCounty() {
        return nameOfCounty;
    }

    public void setNameOfCounty(String nameOfCounty) {
        this.nameOfCounty = nameOfCounty;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(nameOfCounty + " {");
        sb.append("ID = '").append(id).append('\'');
        sb.append(", cities = ").append(cities);
        sb.append('}');
        return sb.toString();
    }
}
