package finalTaskTest;

import main.ua.university.finalTask.bll.country.Country;
import main.ua.university.finalTask.bll.country.CountryHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CountryHelperTest {
    CountryHelper countryHelper;
    List<Country> countries;

    @BeforeEach
    void prepareCountryHelperTest() {
        countryHelper = new CountryHelper();
        countries = Defaults.getCountries();
    }

    @Test
    void findNeededCountryByCountryListAndName() {
        Country country = countryHelper.findNeededCountry(countries, Defaults.firstCountry.getCountryName());
        Assertions.assertEquals(country, Defaults.firstCountry);
    }

    @Test
    void findNeededCountryByCountryListAndId() {
        Country country = countryHelper.findNeededCountry(countries, Defaults.firstCountry.getId());
        Assertions.assertEquals(country, Defaults.firstCountry);
    }

    @Test
    void findNeededCountryByCity() {
        Country country = countryHelper.findNeededCountryByCity(countries, Defaults.warsaw);
        Assertions.assertEquals(country, Defaults.secondCountry);
    }

    @Test
    void findNeededCountryByCityReturnWrongCountry() {
        Country country = countryHelper.findNeededCountryByCity(countries, Defaults.warsaw);
        Assertions.assertNotEquals(country, Defaults.firstCountry);
    }

    @Test
    void isThisCountryExistByName() {
        boolean isThisCountryExist = countryHelper.isThisCountryExist(countries, Defaults.firstCountry.getCountryName());
        Assertions.assertTrue(isThisCountryExist);
    }

    @Test
    void isThisCountryExistByNameReturnFalse() {
        boolean isThisCountryExist = countryHelper.isThisCountryExist(countries, "Norway");
        Assertions.assertFalse(isThisCountryExist);
    }

    @Test
    void isThisCountryExistById() {
        boolean isThisCountryExist = countryHelper.isThisCountryExist(countries, Defaults.firstCountry.getId());
        Assertions.assertTrue(isThisCountryExist);
    }

    @Test
    void isThisCountryExistByIdReturnFalse() {
        boolean isThisCountryExist = countryHelper.isThisCountryExist(countries, 111111);
        Assertions.assertFalse(isThisCountryExist);
    }

    @Test
    void doesCountryHaveCapitalReturnTrue() {
        boolean doesCountryHaveCapital = countryHelper.doesCountryHaveCapital(Defaults.firstCountry);
        Assertions.assertTrue(doesCountryHaveCapital);
    }

    @Test
    void doesCountryHaveCapitalReturnFalse() {
        boolean doesCountryHaveCapital = countryHelper.doesCountryHaveCapital(Defaults.testCountry);
        Assertions.assertFalse(doesCountryHaveCapital);
    }

    @Test
    void updateCountryInCountryList() {
        Country newCountry = new Country("Test", 194535);
        List<Country> newList = new ArrayList<>(countries);

        int index = countries.indexOf(Defaults.secondCountry);
        newList.set(index, newCountry);

        countries = countryHelper.updateCountryInCountryList(countries, Defaults.secondCountry, newCountry);
        Assertions.assertEquals(countries, newList);
    }

    @Test
    void addNewCountryToCountryList() {
        countries.add(Defaults.testCountry);
        Assertions.assertTrue(countries.contains(Defaults.testCountry));
    }

    @Test
    void deleteCountry() {
        countries.remove(Defaults.secondCountry);
        Assertions.assertFalse(countries.contains(Defaults.secondCountry));
    }

    @Test
    void createNewCountryByName() {
        Country country = countryHelper.createNewCountryByName(Defaults.testCountry.getCountryName(), Defaults.testCountry.getId());
        Assertions.assertEquals(country.getCountryAndCitiesInfoInFormat(), Defaults.testCountry.getCountryAndCitiesInfoInFormat());
    }

    @Test
    void getCountryAfterChangingName() {
        String name = "test";
        Country country = countryHelper.getCountryAfterChangingName(Defaults.firstCountry, name, Defaults.firstCountry.getId());
        Defaults.firstCountry.setCountryName(name);
        Assertions.assertEquals(country.getCountryAndCitiesInfoInFormat(), Defaults.firstCountry.getCountryAndCitiesInfoInFormat());
    }
}