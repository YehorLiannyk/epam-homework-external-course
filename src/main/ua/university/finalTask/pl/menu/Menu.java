package main.ua.university.finalTask.pl.menu;

import main.ua.university.finalTask.bll.City;
import main.ua.university.finalTask.bll.Country;
import main.ua.university.finalTask.bll.CountryHelper;
import main.ua.university.finalTask.dal.DataReader;
import main.ua.university.finalTask.dal.DataWriter;
import main.ua.university.finalTask.pl.GettingValuesFromInput;
import main.ua.university.finalTask.pl.StringConst;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;

public class Menu {
    List<Country> countries;
    MenuHelper menuHelper;

    public Menu() {
        countries = DataReader.readCountriesFromFile();
        menuHelper = new MenuHelper(this, new CountryHelper());
    }

    public void startProgram() {
        System.out.println("Hello, let's start!");
        while (true) {
            callStartMenu();
        }
    }

    private void finishAndSave(List<Country> list) {
        System.out.println(StringConst.WAIT_IM_SAVING_ALL_INFORMATION);
        try {
            DataWriter.writeCountriesToFile(list);
            System.out.println(StringConst.SUCCESS_SAVING);
        } catch (FileNotFoundException e) {
            System.err.println("There is a problem: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println(StringConst.BYE_MSG);
    }

    public void callStartMenu() {
        int value = getStartMenuActionNumber();
        switch (value) {
            case 0: {
                finishAndSave(countries);
                System.exit(0);
                break;
            }
            case 1: {
                menuHelper.printAllCountryAndCities(countries);
                break;
            }
            case 2: {
                callFindMenu();
                break;
            }
            case 3: {
                countries = menuHelper.addNewCountry(countries);
                break;
            }
            case 4: {
                //callDeleteMenu()
                break;
            }
            default:
                break;
        }
    }

    int getStartMenuActionNumber() {
        final int start = 0;
        final int end = 4;
        System.out.println(StringConst.CHOOSE_NEXT_ACTION_MSG);
        System.out.println("1 - Print all countries and cities");
        System.out.println("2 - Find some country/city");
        System.out.println("3 - Add new country");
        System.out.println("4 - Delete some country/city");
        System.out.println(StringConst.MENU_EXIT);
        return GettingValuesFromInput.getValueInRange(start, end);
    }

    void callFindMenu() {
        boolean active = true;
        while (active) {
            int value = getFindMenuActionNumber();
            switch (value) {
                case 0: {
                    active = false;
                    break;
                }
                case 1: {
                    Country country = callCountryCriteriaMenu(countries);
                    menuHelper.showCountryPage(country);
                    break;
                }
                case 2: {
                    City city = callCityCriteriaMenu(countries);
                    menuHelper.showCityPage(city);
                    break;
                }
                default:
                    break;
            }
        }
    }

    int getFindMenuActionNumber() {
        final int start = 0;
        final int end = 2;
        System.out.println(StringConst.CHOOSE_NEXT_ACTION_MSG);
        System.out.println("1 - Find some country");
        System.out.println("2 - Find some city");
        System.out.println(StringConst.MENU_BACK);
        return GettingValuesFromInput.getValueInRange(start, end);
    }

    Country callCountryCriteriaMenu(List<Country> countries) {
        boolean active = true;
        Country country = null;
        while (active) {
            int value = getCountryCriteriaMenuActionNumber();
            switch (value) {
                case 0: {
                    active = false;
                    break;
                }
                case 1: {
                    country = menuHelper.findCountryByName(countries);
                    return country;
                }
                case 2: {
                    country = menuHelper.findCountryById(countries);
                    return country;
                }
                default:
                    break;
            }
        }
        return country;
    }

    int getCountryCriteriaMenuActionNumber() {
        final int start = 0;
        final int end = 2;
        System.out.println(StringConst.CHOOSE_NEXT_ACTION_MSG);
        System.out.println("1 - Find country by name");
        System.out.println("2 - Find country by ID");
        System.out.println(StringConst.MENU_BACK);
        return GettingValuesFromInput.getValueInRange(start, end);
    }

    void callCountryMenu(Country country) {
        boolean active = true;
        while (active) {
            int value = getCountryMenuActionNumber();
            switch (value) {
                case 0: {
                    active = false;
                    break;
                }
                case 1: {
                    countries = menuHelper.deletingCountry(countries, country);
                    active = false;
                    System.out.println(StringConst.COUNTRY_DELETE_MSG);
                    break;
                }
                case 2: {
                    countries = menuHelper.addingNewCityToCountry(countries, country);
                    break;
                }
                case 3: {
                    countries = menuHelper.deletingCityFromCountry(countries, country);
                    break;
                }
                case 4: {
                    menuHelper.printCitiesByCountry(country);
                    break;
                }
                case 5: {

                    break;
                }
                default:
                    break;
            }
        }
        return;
    }

    int getCountryMenuActionNumber() {
        final int start = 0;
        final int end = 5;
        System.out.println(StringConst.CHOOSE_NEXT_ACTION_MSG);
        System.out.println("1 - Delete country");
        System.out.println("2 - Add a new city");
        System.out.println("3 - Delete the city");
        System.out.println("4 - Show list of cities");
        System.out.println("5 - Change country name");
        System.out.println(StringConst.MENU_BACK);
        return GettingValuesFromInput.getValueInRange(start, end);
    }

    void callDeleteMenu() {
        boolean active = true;
        while (active) {
            int value = getDeleteMenuActionNumber();
            switch (value) {
                case 0: {
                    active = false;
                    break;
                }
                case 1: {
                    callCountryCriteriaMenu(countries);
                    break;
                }
                case 2: {

                    break;
                }
                default:
                    break;
            }
        }
    }

    int getDeleteMenuActionNumber() {
        final int start = 0;
        final int end = 2;
        System.out.println(StringConst.CHOOSE_NEXT_ACTION_MSG);
        System.out.println("1 - Delete some country");
        System.out.println("2 - Delete some city");
        System.out.println(StringConst.MENU_BACK);
        return GettingValuesFromInput.getValueInRange(start, end);
    }

    City callCityCriteriaMenu(List<Country> countries) {
        boolean active = true;
        City city = null;
        while (active) {
            int value = getCityCriteriaMenuActionNumber();
            switch (value) {
                case 0: {
                    active = false;
                    break;
                }
                case 1: {
                    city = menuHelper.findCityByNameAtAll(countries);
                    return city;
                }
                case 2: {
                    city = menuHelper.findCityByIdAtAll(countries);
                    return city;
                }
                default:
                    break;
            }
        }
        return city;
    }

    int getCityCriteriaMenuActionNumber() {
        final int start = 0;
        final int end = 2;
        System.out.println(StringConst.CHOOSE_NEXT_ACTION_MSG);
        System.out.println("1 - Find city by name");
        System.out.println("2 - Find city by ID");
        System.out.println(StringConst.MENU_BACK);
        return GettingValuesFromInput.getValueInRange(start, end);
    }

    void callCityMenu(City city) {
        boolean active = true;
        while (active) {
            int value = getCityMenuActionNumber();
            switch (value) {
                case 0: {
                    active = false;
                    break;
                }
                case 1: {
                    try {
                        countries = menuHelper.deletingCity(countries, city);
                        System.out.println(StringConst.COUNTRY_DELETE_MSG);
                        active = false;
                    } catch (NoSuchElementException e) {
                        System.err.println(StringConst.ERR_CITY_DOES_NOT_HAVE_COUNTRY);
                    }
                    break;
                }
                case 2: {
                    countries = menuHelper.updateCityInCountry();
                    break;
                }
                case 3: {
                    countries = menuHelper.deletingCityFromCountry(countries, city);
                    break;
                }
                case 4: {
                    //menuHelper.printCitiesByCountry(city);
                    break;
                }
                case 5: {

                    break;
                }
                default:
                    break;
            }
        }
        return;
    }

    int getCityMenuActionNumber() {
        final int start = 0;
        final int end = 5;
        System.out.println(StringConst.CHOOSE_NEXT_ACTION_MSG);
        System.out.println("1 - Delete the city");
        System.out.println("2 - Change city name");
        System.out.println("3 - Change population");
        System.out.println("4 - Change capital status");
        System.out.println("5 - Change country");
        System.out.println(StringConst.MENU_BACK);
        return GettingValuesFromInput.getValueInRange(start, end);
    }
}
