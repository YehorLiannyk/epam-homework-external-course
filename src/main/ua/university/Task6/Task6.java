package main.ua.university.Task6;

import java.math.BigDecimal;

public class Task6 {
    public static void main(String[] args) {

        // attention! hardcode is below :)
        // just for checking

        Employee[] employees = new Employee[3];

        employees[0] = new SalesPerson("Nick", new BigDecimal(1000), new BigDecimal(150));
        employees[1] = new SalesPerson("Max", new BigDecimal(700), new BigDecimal(97));
        employees[2] = new Manager("Brandon", new BigDecimal(2000), 151);

        Company Microsoft = new Company(employees);

        Microsoft.giveEverybodyBonus(new BigDecimal(270));
        for (var item : Microsoft.getEmployees()) {
            System.out.println(item.getLastName() + " = " + item.getBonus());
        }
    }
}
