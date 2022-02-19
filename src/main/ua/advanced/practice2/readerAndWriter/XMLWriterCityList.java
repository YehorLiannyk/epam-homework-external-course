package main.ua.advanced.practice2.readerAndWriter;

import main.ua.advanced.practice2.City;
import main.ua.university.finalTask.dal.FilePath;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

//
// this class is used for once creating cityList and write it to xml format for next reading
//
public class XMLWriterCityList {
    public static void main(String[] args) throws FileNotFoundException {
        List<City> cityList = new ArrayList<>();
        String ukraine = "Ukraine";
        City kyiv = new City(ukraine, "Kyiv", 3000000, true, 123543);
        City dnipro = new City(ukraine, "Dnipro", 1000000, false, 176543);
        City odesa = new City(ukraine, "Odesa", 1000000, false, 142142);

        String poland = "Poland";
        City warsaw = new City(poland, "Warsaw", 2500000, true, 198753);
        City lublin = new City(poland, "Lublin", 1200000, false, 126643);

        String germany = "Germany";
        City berlin = new City(germany, "Berlin", 3000000, true, 175937);

        cityList.add(kyiv);
        cityList.add(dnipro);
        cityList.add(odesa);
        cityList.add(warsaw);
        cityList.add(lublin);
        cityList.add(berlin);
        writeCitiesToFileTemp(cityList);
    }

    public static void writeCitiesToFileTemp(List<City> cityList) throws FileNotFoundException {
        try (XMLEncoder xmlEncoder = new XMLEncoder(new BufferedOutputStream(
                new FileOutputStream(FilePath.pathDir + "advanced/cities.xml")))) {
            xmlEncoder.writeObject(cityList);
            xmlEncoder.flush();
        }
    }
}

/*package main.ua.advanced.practice2.readerAndWriter;

import main.ua.advanced.practice2.City;
import main.ua.advanced.practice2.City.Country;
import main.ua.advanced.practice2.Container;
import main.ua.advanced.practice2.task1_list.MyList;
import main.ua.advanced.practice2.task1_list.MyListImpl;
import main.ua.university.finalTask.dal.FilePath;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

// the class is used for creating cityList and write it to xml format
// It's not a part of The Final task
//
public class XMLWriterCityList {
    public static void main(String[] args) throws FileNotFoundException {

///////////////////////////////////////////
        Country ukraine = new Country("Ukraine", 123456);
        City kyiv = new City(ukraine, "Kyiv", 3000000, true, 123543);
        City dnipro = new City(ukraine, "Dnipro", 1000000, false, 176543);
        City odesa = new City(ukraine, "Odesa", 1000000, false, 142142);

        City.Country poland = new City.Country("Poland", 654321);
        City warsaw = new City(poland, "Warsaw", 2500000, true, 198753);
        City lublin = new City(poland, "Lublin", 1200000, false, 126643);

        Country germany = new Country("Germany", 640021);
        City berlin = new City(germany, "Berlin", 3000000, true, 175937);
//////////////////////////////////////////

        MyList cityQueue = new MyListImpl<City>();


        cityQueue.addLast(kyiv);
        cityQueue.addLast(dnipro);
        cityQueue.addLast(odesa);
        cityQueue.addLast(warsaw);
        cityQueue.addLast(lublin);
        cityQueue.addLast(berlin);
        writeCitiesToFileTemp(cityQueue,FilePath.pathDir + "advanced/cities_myList.xml");
    }

    public static void writeCitiesToFileTemp(List<City> cityList) throws FileNotFoundException {
        try (XMLEncoder xmlEncoder = new XMLEncoder(new BufferedOutputStream(
                new FileOutputStream(FilePath.pathDir + "advanced/cities.xml")))) {
            xmlEncoder.writeObject(cityList);
            xmlEncoder.flush();
        }
    }

    public static void writeCitiesToFileTemp(Container cityList, String filepath) throws FileNotFoundException {
        Object[] array = cityList.reformatInObjectArray();
        try (XMLEncoder xmlEncoder = new XMLEncoder(new BufferedOutputStream(
                new FileOutputStream(filepath)))) {
            xmlEncoder.writeObject(cityList);
            xmlEncoder.flush();
        }
    }
}
*/
