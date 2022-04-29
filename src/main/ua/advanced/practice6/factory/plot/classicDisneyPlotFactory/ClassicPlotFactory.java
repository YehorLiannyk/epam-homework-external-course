package main.ua.advanced.practice6.factory.plot.classicDisneyPlotFactory;

import main.ua.advanced.practice6.factory.plot.Character;
import main.ua.advanced.practice6.factory.plot.Plot;
import main.ua.advanced.practice6.factory.plot.PlotFactories;
import main.ua.advanced.practice6.factory.plot.PlotFactory;

import java.util.logging.Level;

public class ClassicPlotFactory implements PlotFactory {
    ClassicPlot plot;
    private Character hero;
    private Character beloved;
    private Character villain;

    public ClassicPlotFactory(Character hero, Character beloved, Character villain) {
        this.hero = hero;
        this.beloved = beloved;
        this.villain = villain;
        PlotFactories.logger.log(Level.INFO,
                new StringBuilder("Hero: ").append(hero.name()).append(", beloved: ").append(beloved.name())
                        .append(", villain: ").append(villain.name()).toString()); // just for demonstration
    }

    @Override
    public Plot plot() {
        if (plot == null)
            plot = new ClassicPlot(hero, beloved, villain);
        PlotFactories.logger.log(Level.INFO, plot.toString()); // just for demonstration
        return plot;
    }
}
