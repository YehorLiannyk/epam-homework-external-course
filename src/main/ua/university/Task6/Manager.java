package main.ua.university.Task6;

import java.math.BigDecimal;

public class Manager extends Employee {
    private int quantity;

    public Manager(String lastName, BigDecimal salary, int quantity) {
        super(lastName, salary);
        this.quantity = quantity;
    }

    @Override
    public void setBonus(BigDecimal bonusAmount) {
        bonus = bonusAmount;
        if (quantity > 100 && quantity < 150) {
            bonus = bonus.add(new BigDecimal(500));
        }
        else if (quantity > 150) {
            bonus = bonus.add(new BigDecimal(1000));
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
