package main.ua.advanced.practice8;

import main.ua.advanced.practice8.connection.ConnectionPool;

import java.sql.Connection;

public class MovieLibrary {
    public static void main(String[] args) {
        Connection connection = ConnectionPool.getConnection();

    }
}
