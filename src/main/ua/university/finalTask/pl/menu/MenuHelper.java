package main.ua.university.finalTask.pl.menu;

import main.ua.university.finalTask.bll.*;
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

    Country addNewCityToCountry(Country country, List<Country> countries) {
        City newCity = createNewCityByCountry(country, countries);
        cityHelper.addNewCity(country, newCity);
        return country;
    }

    City createNewCityByCountry(Country country, List<Country> countries) {
        boolean check = true;
        String cityName = "";
        int population = 0;
        boolean isCapital = false;
        while (check) {
            try {
                cityName = GettingValuesFromInput.getString("Input city name: ");
                if (cityHelper.isThisCityExist(country, cityName))
                    throw new IllegalArgumentException();
                population = GettingValuesFromInput.getInt("Input population of the city: ");
                isCapital = isCapital(country);
                check = false;
            } catch (IllegalArgumentException e) {
                System.out.println("There is city with this name already! Choose another name");
            }
        }
        return cityHelper.createNewCity(country, cityName, population, isCapital, getFreeId(countries));
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
        System.out.println("Input city name you want to delete");
        return findCityByName(country);
    }

    void printCitiesByCountry(Country country) {
        if(country.getCities() != null)
            System.out.print(country.getCitiesInfoInFormat(country.getCities()));
        else
            System.out.println("There is no any cities yet");
    }

    List<Country> deletingCountry(List<Country> countries, Country country) {
        return countryHelper.deleteCountry(countries, country);
    }

    List<Country> deletingCity(List<Country> countries, City city) {
        return cityHelper.deleteCity(countries, city);
    }

    List<Country> addingNewCityToCountry(List<Country> countries, Country country) {
        return countryHelper.updateCountryInCountryList(countries, country, addNewCityToCountry(country, countries));
    }

    List<Country> deletingCityFromCountry(List<Country> countries, Country country) {
        if (country.getCities() != null)
            return countryHelper.updateCountryInCountryList(countries, country, deleteCityFromCountry(country));
        else {
            System.out.println("There is no any cities yet");
            return countries;
        }
    }

    List<Country> addNewCountry(List<Country> countries) {
        Country newCountry = createNewCountry(countries);
        return countryHelper.addNewCountryToCountryList(countries, newCountry);
    }

    Country createNewCountry(List<Country> countries) {
        boolean check = true;
        String countryName = "";
        while (check) {
            try {
                countryName = GettingValuesFromInput.getString("Input country name: ");
                if (countryHelper.isThisCountryExist(countries, countryName))
                    throw new IllegalArgumentException();
                check = false;
            } catch (IllegalArgumentException e) {
                System.out.println("There is country with this name already! Choose another name");
            }
        }
        return countryHelper.createNewCountryByName(countryName, getFreeId(countries));
    }

    List<Country> getCountryListAfterCountryNameChange(List<Country> countries, Country country) {
        String newName = getNewCountryName();
        Country countryAfterChangingName = countryHelper.getCountryAfterChangingName(country, newName, getFreeId(countries));
        return countryHelper.updateCountryInCountryList(countries, country, countryAfterChangingName);
    }

    List<Country> getCountryListAfterCityNameChange(List<Country> countries, City city) {
        String newName = getNewCityName();
        City cityAfterChangingName = cityHelper.getCityAfterChangingName(city, newName, getFreeId(countries));
        return cityHelper.updateCityInCountryList(countries, city, cityAfterChangingName);
    }

    List<Country> getCountryListAfterCityPopulationChange(List<Country> countries, City city) {
        int population = getNewCityPopulation();
        City cityAfterChangingPopulation = cityHelper.getCityAfterChangingPopulation(city, population, getFreeId(countries));
        return cityHelper.updateCityInCountryList(countries, city, cityAfterChangingPopulation);
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
            City newCity = cityHelper.getCityAfterChangingCapitalStatus(city, isCapital, getFreeId(countries));
            countries = cityHelper.updateCityInCountryList(countries, city, newCity);
        }
        return countries;
    }


    private boolean wasTrueAndCityNotCapitalAndCountryHasCapitalAlready(City city, boolean isCapital) {
        return isCapital && !city.isCapital() && countryHelper.doesCountryHaveCapital(city.getCountry());
    }

    boolean getNewCityCapitalStatus() {
        return GettingValuesFromInput.getBoolean(StringConst.INPUT_NEW_CAPITAL_STATUS_FOR_CITY);
    }

    int getNewCityPopulation() {
        return GettingValuesFromInput.getInt(StringConst.INPUT_NEW_POPULATION_FOR_THE_CITY);
    }

    String getNewCityName() {
        return GettingValuesFromInput.getString(StringConst.INPUT_NEW_NAME_FOR_THE_CITY);
    }

    String getNewCountryName() {
        return GettingValuesFromInput.getString(StringConst.INPUT_NEW_NAME_FOR_THE_COUNTRY);
    }

    int getFreeId(List<Country> countries) {
        return IDHelper.getFreeID(countries);
    }

}
