package Task6Test;

import main.ua.university.Task6.Company;
import main.ua.university.Task6.Employee;
import main.ua.university.Task6.Manager;
import main.ua.university.Task6.SalesPerson;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class Task6Test {

    //Employee
    @Test
    void toPay() {
        SalesPerson employee = new SalesPerson("", new BigDecimal(1000), new BigDecimal(0));
        employee.setSalary(new BigDecimal(1000));
        employee.setBonus(new BigDecimal(200));
        assertEquals(new BigDecimal(1200), employee.toPay());
    }

    //SalesPerson
    @Test
    void setBonusSalesPerson() {
        SalesPerson salesPerson = new SalesPerson("Nick", new BigDecimal(1000), new BigDecimal(100));
        salesPerson.setBonus(new BigDecimal(200));
        assertEquals(new BigDecimal(200), salesPerson.getBonus());

        salesPerson.setPercent(new BigDecimal(120));
        salesPerson.setBonus(new BigDecimal(200));
        assertEquals(new BigDecimal(400), salesPerson.getBonus());

        salesPerson.setPercent(new BigDecimal(210));
        salesPerson.setBonus(new BigDecimal(200));
        assertEquals(new BigDecimal(600), salesPerson.getBonus());
    }

    //Manager
    @Test
    void setBonusManager() {
        Manager manager = new Manager("Olena", new BigDecimal(1000), 90);
        manager.setBonus(new BigDecimal(200));
        assertEquals(new BigDecimal(200), manager.getBonus());

        manager.setQuantity(120);
        manager.setBonus(new BigDecimal(200));
        assertEquals(new BigDecimal(700), manager.getBonus());

        manager.setQuantity(170);
        manager.setBonus(new BigDecimal(200));
        assertEquals(new BigDecimal(1200), manager.getBonus());
    }

    //Company
    @Test
    void giveEverybodyBonus() {
        Employee[] employees = new Employee[] {
                new SalesPerson("", new BigDecimal(1000), new BigDecimal(100)),
                new SalesPerson("", new BigDecimal(1200), new BigDecimal(150)),
                new Manager("", new BigDecimal(1200), 160)
        };

        Company company = new Company(employees);
        company.giveEverybodyBonus(new BigDecimal(320));

        assertEquals(new BigDecimal(320), company.getEmployees()[0].getBonus());
        assertEquals(new BigDecimal(640), company.getEmployees()[1].getBonus());
        assertEquals(new BigDecimal(1320), company.getEmployees()[2].getBonus());
    }

    @Test
    void totalToPay() {
        Employee[] employees = new Employee[] {
                new SalesPerson("", new BigDecimal(1000), new BigDecimal(100)),
                new SalesPerson("", new BigDecimal(1200), new BigDecimal(150)),
                new Manager("", new BigDecimal(1200), 160)
        };
        Company company = new Company(employees);
        assertEquals(new BigDecimal(3400), company.totalToPay());

        company.giveEverybodyBonus(new BigDecimal(320));
        assertEquals(new BigDecimal(5680), company.totalToPay());
    }

    @Test
    void nameMaxSalary() {
        Employee[] employees = new Employee[] {
                new SalesPerson("Nick", new BigDecimal(1000), new BigDecimal(100)),
                new SalesPerson("Olha", new BigDecimal(1400), new BigDecimal(150)),
                new Manager("Max", new BigDecimal(1200), 160)
        };
        Company company = new Company(employees);
        assertEquals("Olha", company.nameMaxSalary());
    }
}
