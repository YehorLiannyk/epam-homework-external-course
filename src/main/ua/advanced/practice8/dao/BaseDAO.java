package main.ua.advanced.practice8.dao;

import java.sql.Connection;

public abstract class BaseDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
