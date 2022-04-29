package main.ua.advanced.practice6.decorator;

import main.ua.advanced.practice6.factory.plot.PlotFactories;

import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Decorators {
    public static final Logger logger = Logger.getLogger(PlotFactories.class.getName());
    //just for demonstration
    static {
        try {
            Handler fileHandler = new FileHandler("resources/advanced/practice6/" + "decorators.log");
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> evenIndexElementsSubList(List<String> sourceList) {
        return new EvenIndexDecoratorSubList(sourceList);
    }
}
