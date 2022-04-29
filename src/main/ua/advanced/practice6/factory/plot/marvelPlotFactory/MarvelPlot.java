package main.ua.advanced.practice6.factory.plot.marvelPlotFactory;

import main.ua.advanced.practice6.factory.plot.Character;
import main.ua.advanced.practice6.factory.plot.EpicCrisis;
import main.ua.advanced.practice6.factory.plot.Plot;
import main.ua.advanced.practice6.factory.plot.PlotFactories;

import java.util.logging.Level;

class MarvelPlot implements Plot {
    private final String plot;

    public MarvelPlot(Character[] heroes, EpicCrisis epicCrisis, Character villain) {
        this.plot = epicCrisis.name() + " threatens the world. " +
                "But " + getCharactersFromArray(heroes) + " " +
                "on guard. So, no way that intrigues of " + villain.name() + " overcome the willpower of inflexible heroes";
    }

    private String getCharactersFromArray(Character[] characters) {
        StringBuilder result = new StringBuilder();
        if (characters.length > 0) {
            addCharactersToStringBuilder(characters, result);
        } else {
            PlotFactories.logger.log(Level.SEVERE, plot.toString()); // just for demonstration
            throw new IllegalArgumentException();
        }
        return result.toString();
    }

    private void addCharactersToStringBuilder(Character[] characters, StringBuilder result) {
        for (int i = 0; i < characters.length; i++) {
            result.append("brave ").append(characters[i].name());
            if (i < characters.length - 1)
                result.append(", ");
        }
    }

    @Override
    public String toString() {
        return plot;
    }
}
