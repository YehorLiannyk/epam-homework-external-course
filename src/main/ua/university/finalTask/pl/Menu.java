package main.ua.university.finalTask.pl;

import main.ua.university.finalTask.bll.Country;
import main.ua.university.finalTask.bll.CountryHelper;

import java.util.List;

public class Menu {
    List<Country> countries;

    public void callStartMenu() {
        System.out.println("Hello, let's start!");
        int value = getStartMenuActionNumber();
        switch (value) {
            case 0: {
                System.out.println("Goodbye!");
                System.exit(0);
                break;
            }
            case 1: {
                printAllCountryAndCities();
                break;
            }
            case 2: {
                callFindMenu();
                break;
            }
            case 3: {

                break;
            }
            case 4: {

                break;
            }
            default: break;
        }
    }

    int getStartMenuActionNumber() {
        final int start = 0;
        final int end = 4;
        System.out.println(StringConst.CHOOSE_NEXT_ACTION_MSG);
        System.out.println("1 - Print all countries and cities");
        System.out.println("2 - Find some country/city");
        System.out.println("3 - Add new country/city");
        System.out.println("4 - Delete soma country/city");
        System.out.println(StringConst.MENU_EXIT);
        return GettingValuesFromInput.getValueInRange(start, end);
    }

    void printAllCountryAndCities() {

    }

    void callFindMenu() {
        int value = getFindMenuActionNumber();
        switch (value) {
            case 0: {
                return;
            }
            case 1: {
                callCountryCriteriaMenu();
                break;
            }
            case 2: {

                break;
            }
            default: break;
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

    void callCountryCriteriaMenu() {
        int value = getCountryCriteriaMenuActionNumber();
        switch (value) {
            case 0: {
                return;
            }
            case 1: {
                findCountryByName();
                break;
            }
            case 2: {
                findCountryById();
                break;
            }
            default: break;
        }
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

    void findCountryByName() {
        String name = getCountryName();
        Country country = CountryHelper.findNeededCountry(name);
        showCountryPage(country);
    }

    private String getCountryName() {
        String name = GettingValuesFromInput.getString(StringConst.INPUT_COUNTRY_NAME);
        while (!CountryHelper.isThisCountryExist(name)) {
            System.out.println("There is no country with that name. Try again");
            name = GettingValuesFromInput.getString(StringConst.INPUT_COUNTRY_NAME);
        }
        return name;
    }

    void findCountryById() {
        int id = getCountryId();
        Country country = CountryHelper.findNeededCountry(id);
        showCountryPage(country);
    }

    private int getCountryId() {
        int id = GettingValuesFromInput.getObjectId(StringConst.INPUT_COUNTRY_ID);
        while (!CountryHelper.isThisCountryExist(id)) {
            System.out.println("There is no country with that id. Try again");
            id = GettingValuesFromInput.getObjectId(StringConst.INPUT_COUNTRY_ID);
        }
        return id;
    }

    void showCountryPage(Country country) {

    }

    void printCountryInfo(Country country) {

    }
}
