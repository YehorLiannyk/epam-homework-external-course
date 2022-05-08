package main.ua.advanced.practice7;

import main.ua.advanced.practice7.entities.Actor;
import main.ua.advanced.practice7.entities.Movie;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.sql.Connection;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

public class MovieLibrary {
    public static final Logger logger = Logger.getLogger(MovieLibrary.class.getSimpleName());

    static {
        PropertyConfigurator.configure("resources/advanced/practice7/log4j.properties");
    }

    public static void main(String[] args) {
        MovieLibrary library = new MovieLibrary();
        Connection connection = DBConnectionCreator.createConnection();
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);
        DBDataManager dbDataManager = new DBDataManager(connection);

        //show all movies
        List<Movie> movies = dbDataManager.getAllMovies();
        logger.info("All movies at beginning: ");
        library.printMovieList(movies);
        logger.info("");

        // first task
        Calendar minDate = new GregorianCalendar();
        minDate.set(2021, Calendar.JANUARY, 1);
        final List<Movie> movieFromDate = dbDataManager.getMovieListFromProductionDate(minDate);
        logger.info("There are movies produced from " + dateFormat.format(minDate.getTime()) + " below:");
        library.printMovieList(movieFromDate);
        logger.info("");

        //second task
        Movie firstMovie = dbDataManager.getAllMovies().get(0); // use for example first movie
        List<Actor> movieActors = dbDataManager.getActorListOfMovie(firstMovie);
        logger.info("Actors of movie '" + firstMovie.getName() + "' below: ");
        library.printMovieList(movieActors);
        logger.info("");

        //third task
        final int MOVIE_AMOUNT = 2;
        Map<Actor, Integer> movieAmountActorsMap = dbDataManager.getActorsMapByMovieAmount(MOVIE_AMOUNT);
        logger.info("Actors whose movie amount >= " + MOVIE_AMOUNT + " below:");
        library.printMovieMapWithKeyName(movieAmountActorsMap, "movieAmount");
        logger.info("");

        //fourth task
        final int DIRECTOR_MOVIE_AMOUNT = 1; // at least one movie
        Map<Actor, Integer> movieAmountDirectorsMap = dbDataManager.getDirectorMapByMovieAmount(DIRECTOR_MOVIE_AMOUNT);
        logger.info("Actors who was a director >= " + DIRECTOR_MOVIE_AMOUNT + " times below:");
        library.printMovieMapWithKeyName(movieAmountDirectorsMap, "movie director");
        logger.info("");

        //fifth task
        final int MAX_YEAR = 10;
        logger.info("Deleting movies older than " + MAX_YEAR);
        dbDataManager.deleteMoviesOlderThan(MAX_YEAR);
        logger.info("Movie list now: ");
        List<Movie> movieList = dbDataManager.getAllMovies();
        library.printMovieList(movieList);

        DBConnectionCreator.closeConnection(connection);
    }

    private <V> void printMovieList(List<V> vList) {
        vList.forEach(element -> logger.info(element.toString()));
    }

    private <K, V> void printMovieMapWithKeyName(Map<K, V> map, String vName) {
        map.forEach((k, v) -> logger.info(vName + " = " + v + ", " + k.toString()));
    }
}
