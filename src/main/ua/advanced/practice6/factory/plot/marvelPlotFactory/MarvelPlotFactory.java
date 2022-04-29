package main.ua.advanced.practice6.factory.plot.marvelPlotFactory;

import main.ua.advanced.practice6.factory.plot.*;
import main.ua.advanced.practice6.factory.plot.Character;

import java.util.Arrays;
import java.util.logging.Level;

public class MarvelPlotFactory implements PlotFactory {
    private Character[] heroes;
    private EpicCrisis epicCrisis;
    private Character villain;
    MarvelPlot plot;

    public MarvelPlotFactory(Character[] heroes, EpicCrisis epicCrisis, Character villain) {
        this.heroes = heroes;
        this.epicCrisis = epicCrisis;
        this.villain = villain;
        PlotFactories.logger.log(Level.INFO,
                new StringBuilder("Heroes: ").append(Arrays.toString(heroes)).append(", epicCrisis: ").append(epicCrisis.name())
                        .append(", villain: ").append(villain.name()).toString()); // just for demonstration
    }

    @Override
    public Plot plot() {
        if (plot == null)
            plot = new MarvelPlot(heroes, epicCrisis, villain);
        PlotFactories.logger.log(Level.INFO, plot.toString()); // just for demonstration
        return plot;
    }
}
