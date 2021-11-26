package main.ua.university.Task7;

import java.math.BigDecimal;

public class BaseDeposit extends Deposit{
    private BigDecimal percent = new BigDecimal(0.05);

    public BaseDeposit(BigDecimal depositAmount, int depositPeriod) {
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
        BigDecimal value = super.getAmount();
        for (int i = 0; i < getPeriod(); i++) {
            value = value.add(value.multiply(percent));
        }
        value = roundToHundreds(value);
        return value;
    }
}
