package finalTaskTest;

import main.ua.university.finalTask.bll.city.City;
import main.ua.university.finalTask.bll.country.Country;

import java.util.ArrayList;
import java.util.List;

public interface Defaults {
    Country firstCountry = new Country("Ukraine", 123456);
    City kyiv = new City(firstCountry, "Kyiv", 3000000, true, 123543);
    City dnipro = new City(firstCountry, "Dnipro", 1000000, false, 176543);
    City test = new City(firstCountry, "test", 1000000, false, 142142);

    Country secondCountry = new Country("Poland", 654321);
    City warsaw = new City(secondCountry, "Warsaw", 2500000, true, 198753);
    City lublin = new City(secondCountry, "Lublin", 1200000, false, 126643);

    Country testCountry = new Country("Germany", 640021);
    City thirdCountryCity = new City(testCountry, "thirdCountryCity", 1200000, false, 175937);

    static List<Country> getCountries() {
        //First country
        Country fCountry = firstCountry;
        fCountry.setCities(formCityLIstFromCities(kyiv, dnipro));

        //Second country
        Country sCountry = secondCountry;
        sCountry.setCities(formCityLIstFromCities(warsaw, lublin));

        // create country list
        List<Country> countries = new ArrayList<>();
        countries.add(fCountry);
        countries.add(sCountry);
        return countries;
    }

    static List<City> formCityLIstFromCities(City... city) {
        List<City> cityList = new ArrayList<>();
        for (int i = 0; i < city.length; i++)
            cityList.add(city[i]);
        return cityList;
    }
}

