package finalTaskTest;

import main.ua.university.finalTask.bll.IDHelper;
import main.ua.university.finalTask.bll.country.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class IDHelperTest {
    List<Country> countries;

    @BeforeEach
    void setCountries() {
        countries = Defaults.getCountries();
    }

    @Test
    void isThisIDFreeReturnFalse() {
        boolean isThisIDFree = IDHelper.isThisIDFree(Defaults.firstCountry.getId(), countries);
        Assertions.assertFalse(isThisIDFree);
    }

    @Test
    void isThisIDFreeReturnTrue() {
        boolean isThisIDFree = IDHelper.isThisIDFree(989898, countries);
        Assertions.assertTrue(isThisIDFree);
    }

}