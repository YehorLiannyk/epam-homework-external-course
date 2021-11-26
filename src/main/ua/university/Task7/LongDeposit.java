package main.ua.university.Task7;

import java.math.BigDecimal;

public class LongDeposit extends Deposit{
    BigDecimal percent = new BigDecimal(0.15);
    public LongDeposit(BigDecimal depositAmount, int depositPeriod) {
        super(depositAmount, depositPeriod);
    }

    @Override
    public BigDecimal income() {
        BigDecimal income = getAmount();
        income = income.subtract(super.getAmount());
        income = roundToHundreds(income);
        return income;
    }

    @Override
    public BigDecimal getAmount() {
        BigDecimal income = super.getAmount();
        for (int i = 1; i <= getPeriod(); i++) {
            if (i > 6) {
                income = income.add(income.multiply(percent));
            }
        }
        income = roundToHundreds(income);
        return income;
    }
}
