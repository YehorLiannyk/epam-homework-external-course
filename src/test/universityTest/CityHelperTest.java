package test.universityTest;

import main.ua.university.finalTask.bll.city.City;
import main.ua.university.finalTask.bll.city.CityHelper;
import main.ua.university.finalTask.bll.country.Country;
import main.ua.university.finalTask.bll.country.CountryHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import universityTest.finalTaskTest.Defaults;

import java.util.List;

;

class іCityHelperTest {
    CountryHelper countryHelper;
    CityHelper cityHelper;
    List<Country> countries;

    @BeforeEach
    void prepareCityHelperTest() {
        countryHelper = new CountryHelper();
        cityHelper = new CityHelper(countryHelper);
        countries = universityTest.finalTaskTest.Defaults.getCountries();
    }

    @Test
    void findNeededCityByCountryAndName() {
        City checkCity = cityHelper.findNeededCity(universityTest.finalTaskTest.Defaults.firstCountry, universityTest.finalTaskTest.Defaults.kyiv.getCityName());
        Assertions.assertEquals(universityTest.finalTaskTest.Defaults.kyiv, checkCity);
    }

    @Test
    void findNeededCityByCountryAndId() {
        City checkCity = cityHelper.findNeededCity(Defaults.firstCountry, Defaults.kyiv.getId());
        Assertions.assertEquals(Defaults.kyiv, checkCity);
    }

    @Test
    void findNeededCityByCountryListAndName() {
        City checkCity = cityHelper.findNeededCity(countries, Defaults.kyiv.getCityName());
        Assertions.assertEquals(Defaults.kyiv, checkCity);
    }

    @Test
    void findNeededCityByCountryListAndId() {
        City checkCity = cityHelper.findNeededCity(countries, Defaults.kyiv.getId());
        Assertions.assertEquals(Defaults.kyiv, checkCity);
    }

    @Test
    void findNeededCityByCountryListAndNameThrowExceptionIfCityIsNull() {
        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> {
             cityHelper.findNeededCity(countries, new City().getCityName());
        });
        String expectedMessage = "NoSuchElementException";
        String actualMessage = exception.toString();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void deleteCity() {
        countries = cityHelper.deleteCity(countries, Defaults.kyiv);
        int indexOfFirstCountry = countries.indexOf(Defaults.firstCountry);
        Assertions.assertFalse(countries.get(indexOfFirstCountry).getCities().contains(Defaults.kyiv));
    }

    @Test
    void deleteCityCheckNotDeleteOtherCities() {
        countries = cityHelper.deleteCity(countries, Defaults.kyiv);
        int indexOfFirstCountry = countries.indexOf(Defaults.firstCountry);
        int indexOfSecondCountry = countries.indexOf(Defaults.secondCountry);

        Assertions.assertTrue(countries.get(indexOfFirstCountry).getCities().contains(Defaults.dnipro));
        Assertions.assertTrue(countries.get(indexOfSecondCountry).getCities().contains(Defaults.warsaw));
        Assertions.assertTrue(countries.get(indexOfSecondCountry).getCities().contains(Defaults.lublin));
    }

    @Test
    void getCountryWithAddedNewCity() {
        Country newCountry = cityHelper.addNewCity(Defaults.firstCountry, Defaults.test);
        Assertions.assertTrue(newCountry.getCities().contains(Defaults.test));
    }

    @Test
    void isThisCityExistByCountryAndName() {
        boolean thisCityExist = cityHelper.isThisCityExist(Defaults.firstCountry, Defaults.kyiv.getCityName());
        Assertions.assertTrue(thisCityExist);
    }

    @Test
    void isThisCityExistByCountryAndId() {
        boolean thisCityExist = cityHelper.isThisCityExist(Defaults.firstCountry, Defaults.kyiv.getId());
        Assertions.assertTrue(thisCityExist);
    }

    @Test
    void isThisCityExistByCountryAndIdReturnFalse() {
        boolean thisCityExist = cityHelper.isThisCityExist(Defaults.secondCountry, Defaults.kyiv.getId());
        Assertions.assertFalse(thisCityExist);
    }

    @Test
    void isThisCityExistByCountryListAndName() {
        boolean thisCityExist = cityHelper.isThisCityExist(countries, Defaults.kyiv.getCityName());
        Assertions.assertTrue(thisCityExist);
    }

    @Test
    void isThisCityExistByCountryListAndId() {
        boolean thisCityExist = cityHelper.isThisCityExist(countries, Defaults.kyiv.getId());
        Assertions.assertTrue(thisCityExist);
    }

    @Test
    void isThisCityExistInCountry() {
        boolean isThisCityExistInCountry = cityHelper.isThisCityExistInCountry(Defaults.firstCountry, Defaults.kyiv);
        Assertions.assertTrue(isThisCityExistInCountry);
    }

    @Test
    void isThisCityExistInCountryReturnFalse() {
        boolean isThisCityExistInCountry = cityHelper.isThisCityExistInCountry(Defaults.firstCountry, Defaults.warsaw);
        Assertions.assertFalse(isThisCityExistInCountry);
    }

    @Test
    void getCityAfterChangingName() {
        String cityName = "Lviv";
        City newCity = cityHelper.getCityAfterChangingName(Defaults.kyiv, cityName, Defaults.kyiv.getId());
        Assertions.assertEquals(newCity.getCityName(), cityName);
    }

    @Test
    void getCityAfterChangingPopulation() {
        int population = 1000000;
        City newCity = cityHelper.getCityAfterChangingPopulation(Defaults.kyiv, population, Defaults.kyiv.getId());
        Assertions.assertEquals(newCity.getPopulation(), population);
    }

    @Test
    void getCityAfterChangingCapitalStatusForCapitalReturnFalse() {
        boolean isCapital = false;
        City newCity = cityHelper.getCityAfterChangingCapitalStatus(Defaults.kyiv, isCapital, Defaults.kyiv.getId());
        Assertions.assertFalse(newCity.isCapital());
    }

    @Test
    void getCityAfterChangingCapitalStatusForCapitalReturnTrue() {
        boolean isCapital = true;
        City newCity = cityHelper.getCityAfterChangingCapitalStatus(Defaults.kyiv, isCapital, Defaults.kyiv.getId());
        Assertions.assertTrue(newCity.isCapital());
    }

    @Test
    void getCityAfterChangingCapitalStatusReturnTrueIfCountryHasNotCapital() {
        boolean isCapital = true;
        City newCity = cityHelper.getCityAfterChangingCapitalStatus(Defaults.thirdCountryCity, isCapital, Defaults.thirdCountryCity.getId());
        Assertions.assertTrue(newCity.isCapital());
    }

}