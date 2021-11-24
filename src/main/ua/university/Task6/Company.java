package main.ua.university.Task6;

import java.math.BigDecimal;

public class Company {
    public Employee[] getEmployees() {
        return employees;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    private Employee[] employees;

    public Company(Employee ... employees) {
        this.employees = employees;
    }

    public void giveEverybodyBonus(BigDecimal companyBonus) {
        for (var item : employees) {
            item.setBonus(companyBonus);
        }
    }

    public BigDecimal totalToPay() {
        BigDecimal total = new BigDecimal(0);
        for (var item : employees) {
            total = total.add(item.toPay());
        }
        return total;
    }

    public String nameMaxSalary() {
        String name = "";
        BigDecimal temp = employees[0].toPay();
        for (int i = 0; i < employees.length - 1; i++) {
            if (temp.compareTo(employees[i].toPay()) == -1) {
                temp = employees[i].toPay();
                name = employees[i].getLastName();
            }
        }
        return name;
    }
}
