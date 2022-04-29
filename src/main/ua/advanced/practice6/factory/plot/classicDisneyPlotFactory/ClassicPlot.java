package main.ua.advanced.practice6.factory.plot.classicDisneyPlotFactory;

import main.ua.advanced.practice6.factory.plot.Character;
import main.ua.advanced.practice6.factory.plot.Plot;

class ClassicPlot implements Plot {
    private final String plot;

    public ClassicPlot(Character hero, Character beloved, Character villain) {
        this.plot = hero.name() + " is great. " + hero.name() + " and " + beloved.name()
                + " love each other. " + villain.name() + " interferes with their happiness but loses in the end.";
    }

    @Override
    public String toString() {
        return plot;
    }
}
