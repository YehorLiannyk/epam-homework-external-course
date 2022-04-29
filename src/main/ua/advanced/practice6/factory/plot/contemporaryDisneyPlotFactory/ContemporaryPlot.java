package main.ua.advanced.practice6.factory.plot.contemporaryDisneyPlotFactory;

import main.ua.advanced.practice6.factory.plot.Character;
import main.ua.advanced.practice6.factory.plot.EpicCrisis;
import main.ua.advanced.practice6.factory.plot.Plot;

class ContemporaryPlot implements Plot {
    private final String plot;

    public ContemporaryPlot(Character hero, EpicCrisis epicCrisis, Character funnyFriend) {
        this.plot = hero.name() + " feels a bit awkward and uncomfortable. But personal issues fades, " +
                "when a big trouble comes - " + epicCrisis.name() + ". " + hero.name() + " stands up against it, " +
                "but with no success at first.But putting self together and help of friends, including spectacular " +
                "funny " + funnyFriend.name() + " restore the spirit and " + hero.name() + " overcomes the crisis and " +
                "gains gratitude and respect";
    }

    @Override
    public String toString() {
        return plot;
    }
}
