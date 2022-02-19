package main.ua.university.finalTask.dal;

import main.ua.university.finalTask.bll.city.City;
import main.ua.university.finalTask.bll.country.Country;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

// the class is used for creating cityList and write it to xml format
// It's not a part of The Final task
//
public class XmlCreatorCityListForOtherTasks {
    public static void main(String[] args) throws FileNotFoundException {
        List<City> cityList = new ArrayList<>();
        Country ukraine = new Country("Ukraine", 123456);
        City kyiv = new City(ukraine, "Kyiv", 3000000, true, 123543);
        City dnipro = new City(ukraine, "Dnipro", 1000000, false, 176543);
        City odesa = new City(ukraine, "Odesa", 1000000, false, 142142);

        Country poland = new Country("Poland", 654321);
        City warsaw = new City(poland, "Warsaw", 2500000, true, 198753);
        City lublin = new City(poland, "Lublin", 1200000, false, 126643);

        Country germany = new Country("Germany", 640021);
        City berlin = new City(germany, "Berlin", 3000000, true, 175937);

        cityList.add(kyiv);
        cityList.add(dnipro);
        cityList.add(odesa);
        cityList.add(warsaw);
        cityList.add(lublin);
        cityList.add(berlin);
        writeCountriesToFileTemp(cityList);
    }

    public static void writeCountriesToFileTemp(List<City> cityList) throws FileNotFoundException {
        try (XMLEncoder xmlEncoder = new XMLEncoder(new BufferedOutputStream(
                new FileOutputStream(FilePath.pathDir + "advanced/cities.xml")))) {
            xmlEncoder.writeObject(cityList);
            xmlEncoder.flush();
        }
    }
}
