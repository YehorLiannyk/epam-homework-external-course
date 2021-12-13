package main.ua.university.Task10;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<User> users = getUsers();
        UserHelper.start(users);
    }

    public static  List<User> getUsers() throws IOException {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < UserHelper.URLs.length; i++) {
            List<User> tempList = UserHelper.getUsersFromURL(UserHelper.URLs[i][1]);
            users.addAll(tempList);
        }
        return users;
    }

}
