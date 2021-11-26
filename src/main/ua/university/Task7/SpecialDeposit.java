package main.ua.university.Task7;

import java.math.BigDecimal;

public class SpecialDeposit extends Deposit {
    BigDecimal percent = new BigDecimal(0.01);

    public SpecialDeposit(BigDecimal depositAmount, int depositPeriod) {
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
        for (int i = 0; i < getPeriod(); i++) {
            income = income.add(income.multiply(percent));
            percent = percent.add(new BigDecimal(0.01));
        }
        income = roundToHundreds(income);
        return income;
    }
}
