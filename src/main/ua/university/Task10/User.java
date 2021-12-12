package main.ua.university.Task10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class User {
    String name;
    String position;
    double salary;

    public User(String name, String position, double salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
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

    static boolean isNumberOrPoint(char s) {
        char[] numbersAndPoint = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', ','};
        for (char c : numbersAndPoint)
            if (s == c)
                return true;
        return false;
    }

    @Override
    public String toString() {
        return name +
                " (" + position + ')' +
                ", salary = " + salary;
    }
}
