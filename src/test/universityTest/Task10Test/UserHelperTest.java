package universityTest.Task10Test;

import main.ua.university.Task10.User;
import main.ua.university.Task10.UserHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class UserHelperTest {
    String[][] URLs;
    List<User> users = new ArrayList<>();

    @BeforeEach
    void setTest() {
        URLs = new String[][]{
                {"January 2019", "https://data.gov.ua/dataset/770cc750-4333-424f-b6e9-6e6c5c76aec9/resource/d2b1a7d2-9222-4dfa-b57e-c0bb0b21485b/download/sichen-zp-2019.csv"},
                {"February 2019", "https://data.gov.ua/dataset/770cc750-4333-424f-b6e9-6e6c5c76aec9/resource/38ef4e5e-72ec-4715-95d7-28c0fd42176c/download/liutii-zp-2019.csv"}
        };
        users.add(new User("Max", "", 1500));
        users.add(new User("Daria", "", 2500));
        users.add(new User("Serhiy", "", 3500));
        users.add(new User("Mark", "", 500));
        users.add(new User("Olena", "", 1200));
        users.add(new User("Denys", "", 700));
        users.add(new User("Andriy", "", 800));

    }

    @Test
    void isNumberOrPointGetTrue() {
        char[] numbersAndPoint = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', ','};
        for (char c : numbersAndPoint) {
            Assertions.assertTrue(UserHelper.isNumberOrPoint(c));
        }
    }

    @Test
    void isNumberOrPointGetFalse() {
        char[] symbols = {'a', '@', '=', '`', '/', '*', ' '};
        for (char c : symbols) {
            Assertions.assertFalse(UserHelper.isNumberOrPoint(c));
        }
    }

    @Test
    void getTheHighestSalary() {
        Assertions.assertEquals(3500, UserHelper.getTheHighestSalary(users).getSalary());
    }

    @Test
    void getListOfUsersWithLowestSalary() {
        List<User> test = new ArrayList<>();
        test.add(new User("Mark", "", 500));
        test.add(new User("Denys", "", 700));
        test.add(new User("Andriy", "", 800));
        test.add(new User("Olena", "", 1200));
        test.add(new User("Max", "", 1500));

        Assertions.assertEquals(Arrays.toString(test.toArray()), Arrays.toString(UserHelper.getListOfUsersWithLowestSalary(users).toArray()));
    }

    @Test
    void getListOfUsersWithAverageSalary() {
        List<User> test = new ArrayList<>();
        test.add(new User("Max", "", 1500));

        Assertions.assertEquals(Arrays.toString(test.toArray()), Arrays.toString(UserHelper.getListOfUsersWithAverageSalary(users).toArray()));
    }

    @Test
    void findAverageSalary() {
        double average = 1528.57;
        Assertions.assertEquals(average, UserHelper.findAverageSalary(users));
    }

    @Test
    void roundToTwoNumbers() {
        double waitValue = 1528.57;
        double realValue = 1528.570;
        Assertions.assertEquals(waitValue, UserHelper.round(realValue, 2));

        realValue = 1528.571;
        Assertions.assertEquals(waitValue, UserHelper.round(realValue, 2));

        waitValue = 1528.58;
        realValue = 1528.575;
        Assertions.assertEquals(waitValue, UserHelper.round(realValue, 2));

        realValue = 1528.579;
        Assertions.assertEquals(waitValue, UserHelper.round(realValue, 2));
    }

    @Test
    void roundToThreeNumbers() {
        double waitValue = 1528.572;
        double realValue = 1528.5719;
        Assertions.assertEquals(waitValue, UserHelper.round(realValue, 3));

        realValue = 1528.571999999;
        Assertions.assertEquals(waitValue, UserHelper.round(realValue, 3));

        waitValue = 1528.580;
        realValue = 1528.579999;
        Assertions.assertEquals(waitValue, UserHelper.round(realValue, 3));
    }

    @Test
    void findMonthWithHighestAverSalary() throws IOException {
        String[] monthAndSalary = new String[] {"February 2019", "13650.2"};
        Assertions.assertArrayEquals(monthAndSalary, UserHelper.findMonthWithHighestAverSalary(URLs));
    }
}