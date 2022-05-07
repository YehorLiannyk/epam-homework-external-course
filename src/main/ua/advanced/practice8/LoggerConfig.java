package main.ua.advanced.practice8;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.Arrays;

public class LoggerConfig {
    private static final String FILE_NAME = "resources/advanced/practice8/log4j.properties";

    private LoggerConfig() {}

    public static Logger getLogger(String className) {
        final Logger logger = Logger.getLogger(className);
        PropertyConfigurator.configure(FILE_NAME);
        return logger;
    }

    public static String exceptionMsg(Exception e) {
        return e.getMessage() + Arrays.toString(e.getStackTrace());
    }
}
