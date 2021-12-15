package main.ua.university.finalTask.bll;

import java.io.Serializable;

/*@XmlRootElement(name = "city")
@XmlAccessorType(XmlAccessType.FIELD)*/
public class City implements Serializable {
    /*@XmlAttribute(name = "id")*/
    private int id;
    /*@XmlTransient*/
    //@XmlElement(name = "country")
    private Country country;
    /*@XmlElement(name = "nameOfCity")*/
    private String nameOfCity;
    /*@XmlElement(name = "peopleAmount")*/
    private int peopleAmount;
    /*@XmlElement(name = "isCapital")*/
    private boolean isCapital = false;

    public City(Country country, String nameOfCity, int peopleAmount, boolean isCapital) {
        this.country = country;
        this.nameOfCity = nameOfCity;
        this.peopleAmount = peopleAmount;
        this.isCapital = isCapital;
        id = IDHelper.getFreeID();
    }

    public City() {}

    public City(Country country, String nameOfCity, int peopleAmount) {
        this(country, nameOfCity, peopleAmount, false);
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNameOfCity() {
        return nameOfCity;
    }

    public void setNameOfCity(String nameOfCity) {
        this.nameOfCity = nameOfCity;
    }

    public int getPeopleAmount() {
        return peopleAmount;
    }

    public void setPeopleAmount(int peopleAmount) {
        this.peopleAmount = peopleAmount;
    }

    public boolean isCapital() {
        return isCapital;
    }

    public void setCapital(boolean capital) {
        isCapital = capital;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(nameOfCity);
        if (isCapital)
            sb.append(" (capital)");
        sb.append(" {");
        sb.append("ID = '").append(id).append('\'');
        sb.append(", country = ").append(country.getNameOfCounty());
        sb.append(", people amount = ").append(peopleAmount);
        sb.append('}');
        return sb.toString();
    }
}
