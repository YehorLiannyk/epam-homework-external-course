package main.ua.university.Task10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class UserHelper {
    static final int AMOUNT_OF_USERS_IN_LIST = 5;

    static String[][] URLs = {
            {"January 2019", "https://data.gov.ua/dataset/770cc750-4333-424f-b6e9-6e6c5c76aec9/resource/d2b1a7d2-9222-4dfa-b57e-c0bb0b21485b/download/sichen-zp-2019.csv"},
            {"February 2019", "https://data.gov.ua/dataset/770cc750-4333-424f-b6e9-6e6c5c76aec9/resource/38ef4e5e-72ec-4715-95d7-28c0fd42176c/download/liutii-zp-2019.csv"},
            {"March 2019", "https://data.gov.ua/dataset/770cc750-4333-424f-b6e9-6e6c5c76aec9/resource/953aae94-8c82-4296-881f-f57b3a7be389/download/berezen-2019.csv"}
    };

    public static void start(List<User> users) throws IOException {
        printHighestSalary(users);
        System.out.println();
        printListOfUsersWithLowestSalary(users);
        System.out.println();
        printListOfUsersWithAverageSalary(users);
        System.out.println();
        printAverageSalaryInEveryMonth(URLs);
        System.out.println();

        printMonthWithHighestAverSalary(URLs);
    }

    public static List<User> getUsersFromURL(String url) throws IOException {
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                connection.getInputStream(), Charset.forName("UTF-8")))) {
            br.readLine();
            while (br.ready()) {
                String text = br.readLine();
                String[] words = text.split(";");
                try {
                    users.add(new User(
                            words[0],
                            words[1],
                            getParseDouble(words[2])
                    ));
                } catch (ArrayIndexOutOfBoundsException e) {
                    users.add(new User(
                            "Error",
                            "Error",
                            0
                    ));
                }
            }
        }
        return users;
    }

    private static double getParseDouble(String word) {
        word = deleteAllLetters(word);
        try {
            if (word.contains(","))
                return Double.parseDouble(word.replace(",", "."));
            else
                return Double.parseDouble(word);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static String deleteAllLetters(String word) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (isNumberOrPoint(word.charAt(i)))
                result.append(word.charAt(i));
        }
        return String.valueOf(result);
    }

    public static boolean isNumberOrPoint(char s) {
        char[] numbersAndPoint = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', ','};
        for (char c : numbersAndPoint)
            if (s == c)
                return true;
        return false;
    }

    static void printHighestSalary(List<User> users) {
        User richMan = getTheHighestSalary(users);
        System.out.println("The highest salary has " + richMan.getName() + " and it's equal to " + richMan.getSalary());
    }

    public static User getTheHighestSalary(List<User> users) {
        User richMan = users.get(0);
        for (int i = 0; i < users.size(); i++) {
            if (richMan.getSalary() < users.get(i).getSalary())
                richMan = users.get(i);
        }
        return richMan;
    }

    static void printListOfUsersWithLowestSalary(List<User> users) {
        users = getListOfUsersWithLowestSalary(users);
        System.out.println("List of top " + AMOUNT_OF_USERS_IN_LIST + " the poorest men: ");
        printUsers(users);
    }

    static void printUsers(List<User> users) {
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    public static List<User> getListOfUsersWithLowestSalary(List<User> users) {
        List<User> poorMen = new ArrayList<>();
        for (int i = 0; i < AMOUNT_OF_USERS_IN_LIST; i++) {
            User tempUser = users.get(0);
            for (var user : users) {
                if (tempUser.getSalary() > user.getSalary()) {
                    tempUser = user;
                }
            }
            users.remove(tempUser);
            poorMen.add(tempUser);
        }
        return poorMen;
    }

    static void printListOfUsersWithAverageSalary(List<User> users) {
        users = getListOfUsersWithAverageSalary(users);
        System.out.println("Men with the average salary:");
        printUsers(users);
    }

    public static List<User> getListOfUsersWithAverageSalary(List<User> users) {
        final double percentOfRange = 0.2;
        double averageSalary = findAverageSalary(users);
        double highestRange = averageSalary + averageSalary * percentOfRange;
        double lowestRange = averageSalary - averageSalary * percentOfRange;

        List<User> averageMen = new ArrayList<>();
        for (User user : users) {
            if (user.getSalary() >= lowestRange && user.getSalary() <= highestRange)
                averageMen.add(user);
        }
        return averageMen;
    }

    public  static double findAverageSalary(List<User> users) {
        double averageSalary = 0;
        double sum = 0;
        for (int i = 0; i < users.size(); i++) {
            sum += users.get(i).getSalary();
        }
        averageSalary = round(sum / users.size(), 2);
        return averageSalary;

    }

    public  static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    static void printAverageSalaryInEveryMonth(String[][] URLs) throws IOException {
        System.out.println("Average salary in every month:");
        List<User> users;
        for (String[] url : URLs) {
            users = getUsersFromURL(url[1]);
            String month = url[0];
            double averageSalary = findAverageSalary(users);
            System.out.println('\t' +"Average salary in " + month + " is equal to " + averageSalary);
        }
    }

    static void printMonthWithHighestAverSalary(String[][] URLs) throws IOException {
        String[] monthAndSalary = findMonthWithHighestAverSalary(URLs);
        System.out.println("The highest average salary was in " + monthAndSalary[0] + " and was equal to " + monthAndSalary[1]);
    }

    public static String[] findMonthWithHighestAverSalary(String[][] URLs) throws IOException {
        String[] monthAndSalary;
        double highest = 0;
        String month = "";
        List<User> users;
        for (var item : URLs) {
            users = getUsersFromURL(item[1]);
            double averageSalary = findAverageSalary(users);
            if (averageSalary > highest) {
                highest = averageSalary;
                month = item[0];
            }
        }
        monthAndSalary = new String[] {month, String.valueOf(highest)};
        return monthAndSalary;
    }
}
