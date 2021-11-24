package main.ua.university.Task6;

import java.math.BigDecimal;

public abstract class Employee {
    private String lastName;
    private BigDecimal salary = new BigDecimal(0);
    protected BigDecimal bonus = new BigDecimal(0);

    public Employee(String lastName, BigDecimal salary) {
        this.lastName = lastName;
        this.salary = salary;
    }

    abstract void setBonus(BigDecimal bonusAmount);

    public BigDecimal getBonus() {
        return bonus;
    }

    public String getLastName() {
        return lastName;
    }

    public BigDecimal toPay() {
        return salary.add(bonus);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
