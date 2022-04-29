package main.ua.advanced.practice6.factory.plot;

import main.ua.advanced.practice6.factory.plot.classicDisneyPlotFactory.ClassicPlotFactory;
import main.ua.advanced.practice6.factory.plot.contemporaryDisneyPlotFactory.ContemporaryPlotFactory;
import main.ua.advanced.practice6.factory.plot.marvelPlotFactory.MarvelPlotFactory;

import java.io.IOException;
import java.util.logging.*;

public class PlotFactories {
    public static final Logger logger = Logger.getLogger(PlotFactories.class.getName());
    //just for demonstration
    static {
        try {
            Handler fileHandler = new FileHandler("resources/advanced/practice6/" + "factory_plot.log");
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PlotFactory classicDisneyPlotFactory(Character hero, Character beloved, Character villain) {
        return new ClassicPlotFactory(hero, beloved, villain);
    }

    public PlotFactory contemporaryDisneyPlotFactory(Character hero, EpicCrisis epicCrisis, Character funnyFriend) {
        return new ContemporaryPlotFactory(hero, epicCrisis, funnyFriend);
    }

    public PlotFactory marvelPlotFactory(Character[] heroes, EpicCrisis epicCrisis, Character villain) {
        return new MarvelPlotFactory(heroes, epicCrisis, villain);
    }
}
