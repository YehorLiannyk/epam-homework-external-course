package Task7Test;

import main.ua.university.Task7.BaseDeposit;
import main.ua.university.Task7.LongDeposit;
import main.ua.university.Task7.SpecialDeposit;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class Task7Test {

    @Test
    void getAmountBaseDeposit() {
        BaseDeposit baseDeposit = new BaseDeposit(new BigDecimal(1000), 3);
        BigDecimal check = baseDeposit.roundToHundreds(new BigDecimal(1157.63));
        assertEquals(check, baseDeposit.getAmount());
    }

    @Test
    void incomeBaseDeposit() {
        BaseDeposit baseDeposit = new BaseDeposit(new BigDecimal(1000), 3);
        BigDecimal check = baseDeposit.roundToHundreds(new BigDecimal(157.63));
        assertEquals(check, baseDeposit.income());
    }

    @Test
    void getAmountSpecialDeposit() {
        SpecialDeposit specialDeposit = new SpecialDeposit(new BigDecimal(1000), 2);
        BigDecimal check = specialDeposit.roundToHundreds(new BigDecimal(1030.20));
        assertEquals(check, specialDeposit.getAmount());
    }

    @Test
    void incomeSpecialDeposit() {
        SpecialDeposit specialDeposit = new SpecialDeposit(new BigDecimal(1000), 2);
        BigDecimal check = specialDeposit.roundToHundreds(new BigDecimal(30.20));
        assertEquals(check, specialDeposit.income());
    }

    @Test
    void getAmountLongDeposit() {
        LongDeposit longDeposit = new LongDeposit(new BigDecimal(1000), 2);
        BigDecimal check = longDeposit.roundToHundreds(new BigDecimal(1000));
        assertEquals(check, longDeposit.getAmount());

        longDeposit = new LongDeposit(new BigDecimal(1000), 6);
        check = longDeposit.roundToHundreds(new BigDecimal(1000));
        assertEquals(check, longDeposit.getAmount());

        longDeposit = new LongDeposit(new BigDecimal(1000), 8);
        check = longDeposit.roundToHundreds(new BigDecimal(1322.5));
        assertEquals(check, longDeposit.getAmount());
    }

    @Test
    void incomeLongDeposit() {
        LongDeposit longDeposit = new LongDeposit(new BigDecimal(1000), 2);
        BigDecimal check = longDeposit.roundToHundreds(new BigDecimal(0));
        assertEquals(check, longDeposit.income());

        longDeposit = new LongDeposit(new BigDecimal(1000), 6);
        check = longDeposit.roundToHundreds(new BigDecimal(0));
        assertEquals(check, longDeposit.income());

        longDeposit = new LongDeposit(new BigDecimal(1000), 8);
        check = longDeposit.roundToHundreds(new BigDecimal(322.5));
        assertEquals(check, longDeposit.income());
    }
    
}