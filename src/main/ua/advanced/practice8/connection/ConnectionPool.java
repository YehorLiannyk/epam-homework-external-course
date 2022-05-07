package main.ua.advanced.practice8.connection;

import main.ua.advanced.practice8.LoggerConfig;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionPool {
    private static final String PROPERTIES_FILE = "resources/advanced/database.properties";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/" + "movie_library_2";
    private static final BasicDataSource ds = new BasicDataSource();
    private static final Logger logger = LoggerConfig.getLogger(ConnectionPool.class.getSimpleName());

    static {
        final Properties properties = new Properties();
        try {
            properties.load(new FileReader(PROPERTIES_FILE));
        } catch (IOException e) {
            logger.error(LoggerConfig.exceptionMsg(e));
        }

        ds.setUrl(DATABASE_URL);
        ds.setUsername(properties.getProperty("user"));
        ds.setPassword(properties.getProperty("password"));
        ds.setMinIdle(Integer.parseInt(properties.getProperty("minIdle")));
        ds.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));
        ds.setMaxOpenPreparedStatements((Integer) properties.get("maxOpenPreparedStatements"));
    }

    private ConnectionPool() {
    }

    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            logger.error(LoggerConfig.exceptionMsg(e));
        }
        return null;
    }
}
