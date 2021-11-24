package main.ua.university.Task6;

import java.math.BigDecimal;

public class SalesPerson extends Employee {
    private BigDecimal percent;

    public SalesPerson(String lastName, BigDecimal salary, BigDecimal percent) {
        super(lastName, salary);
        this.percent = percent;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    @Override
    public void setBonus(BigDecimal bonusAmount) {
        bonus = bonusAmount;
        if (percent.compareTo(new BigDecimal(100)) == 1 && percent.compareTo(new BigDecimal(201)) == -1) {
            bonus = bonus.multiply(new BigDecimal(2));
        } else if (percent.compareTo(new BigDecimal(200)) == 1) {
            bonus = bonus.multiply(new BigDecimal(3));
        }
    }

    public void setBonus() {
        setBonus(bonus);
    }
}
