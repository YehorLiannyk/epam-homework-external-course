package main.ua.university.finalTask.pl.menu;

import main.ua.university.finalTask.bll.City;
import main.ua.university.finalTask.bll.CityHelper;
import main.ua.university.finalTask.bll.Country;
import main.ua.university.finalTask.bll.CountryHelper;
import main.ua.university.finalTask.pl.GettingValuesFromInput;
import main.ua.university.finalTask.pl.StringConst;

import java.util.List;
import java.util.NoSuchElementException;

public class MenuHelper {
    CountryHelper countryHelper;
    CityHelper cityHelper;
    Menu menu;

    public MenuHelper(Menu menu, CountryHelper countryHelper, CityHelper cityHelper) {
        this.countryHelper = countryHelper;
        this.cityHelper = cityHelper;
        this.menu = menu;
    }

    public void printAllCountryAndCities(List<Country> countries) {
        countries.forEach((country -> System.out.print(country.getCountryAndCitiesInfoInFormat())));
    }

    Country findCountryByName(List<Country> countries) {
        Country country = null;
        try {
            String name = getCountryName(countries);
            country = countryHelper.findNeededCountry(countries, name);
        } catch (NoSuchElementException e) {
            System.err.println(StringConst.ERR_THERE_IS_NO_COUNTRY);
        }
        return country;
    }

    private String getCountryName(List<Country> countries) {
        String name = GettingValuesFromInput.getString(StringConst.INPUT_COUNTRY_NAME);
        while (!countryHelper.isThisCountryExist(countries, name)) {
            System.out.println(StringConst.NO_COUNTRY_WITH_THE_NAME_MSG);
            name = GettingValuesFromInput.getString(StringConst.INPUT_COUNTRY_NAME);
        }
        return name;
    }

    Country findCountryById(List<Country> countries) {
        Country country = null;
        try {
            int id = getCountryId(countries);
            country = countryHelper.findNeededCountry(countries, id);
        } catch (NoSuchElementException e) {
            System.err.println(StringConst.ERR_THERE_IS_NO_COUNTRY);
        }
        return country;
    }

    private int getCountryId(List<Country> countries) {
        int id = GettingValuesFromInput.getObjectId(StringConst.INPUT_COUNTRY_ID);
        while (!countryHelper.isThisCountryExist(countries, id)) {
            System.out.println(StringConst.NO_COUNTRY_WITH_THE_ID_MSG);
            id = GettingValuesFromInput.getObjectId(StringConst.INPUT_COUNTRY_ID);
        }
        return id;
    }

    City findCityByNameAtAll(List<Country> countries) {
        City city = null;
        try {
            String name = getCityName(countries);
            city = cityHelper.findNeededCity(countries, name);
        } catch (NoSuchElementException e) {
            System.err.println(StringConst.ERR_THERE_IS_NO_CITY);
        }
        return city;
    }

    City findCityByIdAtAll(List<Country> countries) {
        City city = null;
        try {
            int id = getCityId(countries);
            city = cityHelper.findNeededCity(countries, id);
        } catch (NoSuchElementException e) {
            System.err.println(StringConst.ERR_THERE_IS_NO_CITY);
        }
        return city;
    }

    City findCityByName(Country country) {
        City city = null;
        try {
            String name = getCityName(country);
            city = cityHelper.findNeededCity(country, name);
        } catch (NoSuchElementException e) {
            System.err.println(StringConst.ERR_THERE_IS_NO_CITY);
        }
        return city;
    }

    private String getCityName(Country country) {
        String name = GettingValuesFromInput.getString(StringConst.INPUT_CITY_NAME);
        while (!cityHelper.isThisCityExist(country, name)) {
            System.out.println(StringConst.NO_CITY_WITH_THE_NAME_MSG);
            name = GettingValuesFromInput.getString(StringConst.INPUT_CITY_NAME);
        }
        return name;
    }

    private String getCityName(List<Country> countries) {
        String name = GettingValuesFromInput.getString(StringConst.INPUT_CITY_NAME);
        while (!cityHelper.isThisCityExist(countries, name)) {
            System.out.println(StringConst.NO_CITY_WITH_THE_NAME_MSG);
            name = GettingValuesFromInput.getString(StringConst.INPUT_CITY_NAME);
        }
        return name;
    }

    City findCityById(Country country) {
        City city = null;
        try {
            int id = getCityId(country);
            city = cityHelper.findNeededCity(country, id);
        } catch (NoSuchElementException e) {
            System.err.println(StringConst.ERR_THERE_IS_NO_CITY);
        }
        return city;
    }

    private int getCityId(Country country) {
        int id = GettingValuesFromInput.getObjectId(StringConst.INPUT_CITY_ID);
        while (!cityHelper.isThisCityExist(country, id)) {
            System.out.println(StringConst.NO_CITY_WITH_THE_ID_MSG);
            id = GettingValuesFromInput.getObjectId(StringConst.INPUT_CITY_ID);
        }
        return id;
    }

    private int getCityId(List<Country> countries) {
        int id = GettingValuesFromInput.getObjectId(StringConst.INPUT_CITY_ID);
        while (!cityHelper.isThisCityExist(countries, id)) {
            System.out.println(StringConst.NO_CITY_WITH_THE_ID_MSG);
            id = GettingValuesFromInput.getObjectId(StringConst.INPUT_CITY_ID);
        }
        return id;
    }

    void showCountryPage(Country country) {
        printCountryInfo(country);
        menu.callCountryMenu(country);
    }

    void printCountryInfo(Country country) {
        System.out.println(StringConst.COUNTRY_INFO);
        System.out.println(country.getCountryInfoInFormat());
    }

    void showCityPage(City city) {
        printCityInfo(city);
        menu.callCityMenu(city);
    }

    void printCityInfo(City city) {
        System.out.println(StringConst.CITY_INFO);
        System.out.println(city.getCityInfoInFormat());
    }

    Country addNewCityByCountry(Country country) {
        City newCity = createNewCityByCountry(country);
        cityHelper.addNewCity(country, newCity);
        return country;
    }

    City createNewCityByCountry(Country country) {
        String cityName = GettingValuesFromInput.getString("Input city name: ");
        int population = GettingValuesFromInput.getInt("Input population of the city: ");
        boolean isCapital = isCapital(country);
        return new City(country, cityName, population, isCapital);
    }

    private boolean isCapital(Country country) {
        if (countryHelper.doesCountryHaveCapital(country))
            return false;
        else
            return GettingValuesFromInput.getBoolean("Is this city a capital of " + country.getCountryName() + "? ");
    }

    Country deleteCityFromCountry(Country country) {
        List<City> list = country.getCities();
        list.remove(getCityForDelete(country));
        country.setCities(list);
        return country;
    }

    City getCityForDelete(Country country) {
        String cityName = GettingValuesFromInput.getString("Input city name you want to delete: ");
        City city = findCityByName(country);
        return city;
    }

    void printCitiesByCountry(Country country) {
        System.out.print(country.getCitiesInfoInFormat(country.getCities()));
    }

    List<Country> deletingCountry(List<Country> countries, Country country) {
        return countryHelper.deleteCountry(countries, country);
    }

    List<Country> deletingCity(List<Country> countries, City city) {
        return cityHelper.deleteCity(countries, city);
    }

    List<Country> addingNewCityToCountry(List<Country> countries, Country country) {
        return countryHelper.updateCountryInCountryList(countries, country, addNewCityByCountry(country));
    }

    List<Country> deletingCityFromCountry(List<Country> countries, Country country) {
        return countryHelper.updateCountryInCountryList(countries, country, deleteCityFromCountry(country));
    }

    List<Country> addNewCountry(List<Country> countries) {
        Country newCountry = createNewCountry();
        return countryHelper.addNewCountryToCountryList(countries, newCountry);
    }

    Country createNewCountry() {
        String countryName = GettingValuesFromInput.getString("Input country name: ");
        return countryHelper.createNewCountryByName(countryName);
    }

    List<Country> getCountryListAfterCityNameChange(List<Country> countries, City city) {
        String newName = getNewCityName();
        return cityHelper.updateCityInCountryList(countries, city, cityHelper.getCityAfterChangingName(city, newName));
    }

    List<Country> getCountryListAfterCityPopulationChange(List<Country> countries, City city) {
        int population = getNewCityPopulation();
        return cityHelper.updateCityInCountryList(countries, city, cityHelper.getCityAfterChangingPopulation(city, population));
    }

    List<Country> getCountryListAfterCityCapitalStatusChange(List<Country> countries, City city) {
        boolean isCapital = getNewCityCapitalStatus();
        if (wasTrueAndCityNotCapitalAndCountryHasCapitalAlready(city, isCapital)) {
            System.out.println("There is capital in this country already! You need to change country capital first");
        } else if (!isCapital && !city.isCapital()) {
            System.out.println("This this already is not a capital");
        } else if (isCapital && city.isCapital()) {
            System.out.println("This this already is a capital");
        } else {
            City newCity = cityHelper.getCityAfterChangingCapitalStatus(city, isCapital);
            countries = cityHelper.updateCityInCountryList(countries, city, newCity);
        }
        return countries;
    }

    List<Country> getCountryListAfterCityCountryChange(List<Country> countries, City city) {
        Country country = getNewCityCountry(countries);
        if (country == city.getCountry()) {
            System.out.println("The city is in this country already");
        } else {
            City newCity = cityHelper.getCityAfterChangingCounty(city, country);
            countries = cityHelper.updateCityInCountryList(countries, city, newCity);
        }
        return countries;
    }

    Country getNewCityCountry(List<Country> countries) {
        System.out.println("Write name of new country for city");
        return findCountryByName(countries);
    }


    private boolean wasTrueAndCityNotCapitalAndCountryHasCapitalAlready(City city, boolean isCapital) {
        return isCapital && !city.isCapital() && countryHelper.doesCountryHaveCapital(city.getCountry());
    }

    boolean getNewCityCapitalStatus() {
        return GettingValuesFromInput.getBoolean("Input new capital status for city: ");
    }

    int getNewCityPopulation() {
        return GettingValuesFromInput.getInt("Input new population for the city: ");
    }

    String getNewCityName() {
        return GettingValuesFromInput.getString("Input new name for the city: ");
    }

}
