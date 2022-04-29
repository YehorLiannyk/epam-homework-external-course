package main.ua.advanced.practice6.factory.plot.contemporaryDisneyPlotFactory;

import main.ua.advanced.practice6.factory.plot.*;
import main.ua.advanced.practice6.factory.plot.Character;

import java.util.logging.Level;

public class ContemporaryPlotFactory implements PlotFactory {
    private final Character hero;
    private final EpicCrisis epicCrisis;
    private final Character funnyFriend;
    ContemporaryPlot plot;

    public ContemporaryPlotFactory(Character hero, EpicCrisis epicCrisis, Character funnyFriend) {
        this.hero = hero;
        this.epicCrisis = epicCrisis;
        this.funnyFriend = funnyFriend;
        PlotFactories.logger.log(Level.INFO,
                new StringBuilder("Hero: ").append(hero.name()).append(", epicCrisis: ").append(epicCrisis.name())
                        .append(", funnyFriend: ").append(funnyFriend.name()).toString()); // just for demonstration
    }

    @Override
    public Plot plot() {
        if (plot == null)
            plot = new ContemporaryPlot(hero, epicCrisis, funnyFriend);
        PlotFactories.logger.log(Level.INFO, plot.toString()); // just for demonstration
        return plot;
    }
}
