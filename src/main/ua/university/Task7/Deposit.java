package main.ua.university.Task7;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Deposit {
    private BigDecimal amount;
    private int period;

    public Deposit(BigDecimal depositAmount, int depositPeriod) {
        this.amount = depositAmount;
        this.period = depositPeriod;
    }

    abstract BigDecimal income();

    public BigDecimal getAmount() {
        return amount;
    }

    public int getPeriod() {
        return period;
    }

    public static BigDecimal roundToHundreds(BigDecimal number) {
        number = number.setScale(2, RoundingMode.HALF_EVEN);
        return number;
    }
}
