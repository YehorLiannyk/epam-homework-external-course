package test.universityTest;

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
        countries = universityTest.finalTaskTest.Defaults.getCountries();
    }

    @Test
    void findNeededCountryByCountryListAndName() {
        Country country = countryHelper.findNeededCountry(countries, universityTest.finalTaskTest.Defaults.firstCountry.getCountryName());
        Assertions.assertEquals(country, universityTest.finalTaskTest.Defaults.firstCountry);
    }

    @Test
    void findNeededCountryByCountryListAndId() {
        Country country = countryHelper.findNeededCountry(countries, universityTest.finalTaskTest.Defaults.firstCountry.getId());
        Assertions.assertEquals(country, universityTest.finalTaskTest.Defaults.firstCountry);
    }

    @Test
    void findNeededCountryByCity() {
        Country country = countryHelper.findNeededCountryByCity(countries, universityTest.finalTaskTest.Defaults.warsaw);
        Assertions.assertEquals(country, universityTest.finalTaskTest.Defaults.secondCountry);
    }

    @Test
    void findNeededCountryByCityReturnWrongCountry() {
        Country country = countryHelper.findNeededCountryByCity(countries, universityTest.finalTaskTest.Defaults.warsaw);
        Assertions.assertNotEquals(country, universityTest.finalTaskTest.Defaults.firstCountry);
    }

    @Test
    void isThisCountryExistByName() {
        boolean isThisCountryExist = countryHelper.isThisCountryExist(countries, universityTest.finalTaskTest.Defaults.firstCountry.getCountryName());
        Assertions.assertTrue(isThisCountryExist);
    }

    @Test
    void isThisCountryExistByNameReturnFalse() {
        boolean isThisCountryExist = countryHelper.isThisCountryExist(countries, "Norway");
        Assertions.assertFalse(isThisCountryExist);
    }

    @Test
    void isThisCountryExistById() {
        boolean isThisCountryExist = countryHelper.isThisCountryExist(countries, universityTest.finalTaskTest.Defaults.firstCountry.getId());
        Assertions.assertTrue(isThisCountryExist);
    }

    @Test
    void isThisCountryExistByIdReturnFalse() {
        boolean isThisCountryExist = countryHelper.isThisCountryExist(countries, 111111);
        Assertions.assertFalse(isThisCountryExist);
    }

    @Test
    void doesCountryHaveCapitalReturnTrue() {
        boolean doesCountryHaveCapital = countryHelper.doesCountryHaveCapital(universityTest.finalTaskTest.Defaults.firstCountry);
        Assertions.assertTrue(doesCountryHaveCapital);
    }

    @Test
    void doesCountryHaveCapitalReturnFalse() {
        boolean doesCountryHaveCapital = countryHelper.doesCountryHaveCapital(universityTest.finalTaskTest.Defaults.testCountry);
        Assertions.assertFalse(doesCountryHaveCapital);
    }

    @Test
    void updateCountryInCountryList() {
        Country newCountry = new Country("Test", 194535);
        List<Country> newList = new ArrayList<>(countries);

        int index = countries.indexOf(universityTest.finalTaskTest.Defaults.secondCountry);
        newList.set(index, newCountry);

        countries = countryHelper.updateCountryInCountryList(countries, universityTest.finalTaskTest.Defaults.secondCountry, newCountry);
        Assertions.assertEquals(countries, newList);
    }

    @Test
    void addNewCountryToCountryList() {
        countries.add(universityTest.finalTaskTest.Defaults.testCountry);
        Assertions.assertTrue(countries.contains(universityTest.finalTaskTest.Defaults.testCountry));
    }

    @Test
    void deleteCountry() {
        countries.remove(universityTest.finalTaskTest.Defaults.secondCountry);
        Assertions.assertFalse(countries.contains(universityTest.finalTaskTest.Defaults.secondCountry));
    }

    @Test
    void createNewCountryByName() {
        Country country = countryHelper.createNewCountryByName(universityTest.finalTaskTest.Defaults.testCountry.getCountryName(), universityTest.finalTaskTest.Defaults.testCountry.getId());
        Assertions.assertEquals(country.getCountryAndCitiesInfoInFormat(), universityTest.finalTaskTest.Defaults.testCountry.getCountryAndCitiesInfoInFormat());
    }

    @Test
    void getCountryAfterChangingName() {
        String name = "test";
        Country country = countryHelper.getCountryAfterChangingName(universityTest.finalTaskTest.Defaults.firstCountry, name, universityTest.finalTaskTest.Defaults.firstCountry.getId());
        universityTest.finalTaskTest.Defaults.firstCountry.setCountryName(name);
        Assertions.assertEquals(country.getCountryAndCitiesInfoInFormat(), universityTest.finalTaskTest.Defaults.firstCountry.getCountryAndCitiesInfoInFormat());
    }
}