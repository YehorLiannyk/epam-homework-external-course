package universityTest.Task7Test;

import main.ua.university.Task7.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientTest {
    Client client = new Client();;

    @BeforeEach
    void addDepositsToClient() {
        client.addDeposit(new BaseDeposit(new BigDecimal(1000), 3));
        client.addDeposit(new SpecialDeposit(new BigDecimal(1000), 2));
        client.addDeposit(new LongDeposit(new BigDecimal(1000), 8));
    }

    @Test
    void totalIncome() {
        assertEquals(Deposit.roundToHundreds(new BigDecimal(3510.33)), client.totalIncome());
    }

    @Test
    void maxIncome() {
        assertEquals(Deposit.roundToHundreds(new BigDecimal(510.33)), client.maxIncome());
    }

    @Test
    void findFreeIndex() {
        Deposit[] arr = new Deposit[2];
        BigDecimal amount = new BigDecimal(1000);
        arr[0] = new BaseDeposit(amount, 6);
        arr[1] = new BaseDeposit(amount, 6);
        assertEquals(-1, client.findFreeIndex(arr));

        arr[0] = null;
        assertEquals(0, client.findFreeIndex(arr));

        arr[0] = new BaseDeposit(amount, 6);
        arr[1] = null;
        assertEquals(1, client.findFreeIndex(arr));
    }

    @Test
    void getIncomeByNumber() {
        int n = 2;
        assertEquals(Deposit.roundToHundreds(new BigDecimal(30.2)), client.getIncomeByNumber(n));
    }
}
