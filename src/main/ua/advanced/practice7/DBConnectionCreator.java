package main.ua.advanced.practice7;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;

public class DBConnectionCreator {
    private static final Properties properties = new Properties();
    private static final String DATABASE_URL;

    static {
        try {
            properties.load(new FileReader("resources/advanced/practice7/database.properties"));
            String driverName = (String) properties.get("db.driver");
            Class.forName(driverName);
        } catch (IOException | ClassNotFoundException e) {
            MovieLibrary.logger.error(Arrays.toString(e.getStackTrace()));
        }
        DATABASE_URL = (String) properties.get("db.url");
    }

    private DBConnectionCreator() {}

    public static Connection createConnection() {
        try {
            return DriverManager.getConnection(DATABASE_URL, properties);
        } catch (SQLException e) {
            MovieLibrary.logger.error(Arrays.toString(e.getStackTrace()));
        }
        return null;
    }

    public void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            MovieLibrary.logger.error(Arrays.toString(e.getStackTrace()));
        }
    }
}
