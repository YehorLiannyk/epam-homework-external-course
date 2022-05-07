package main.ua.advanced.practice8;

import main.ua.advanced.practice8.connection.ConnectionPool;
import main.ua.advanced.practice8.dao.ActorDAO;
import main.ua.advanced.practice8.dao.MovieDAO;
import main.ua.advanced.practice8.entities.Movie;
import main.ua.advanced.practice8.repository.ActorRepository;
import main.ua.advanced.practice8.repository.MovieRepository;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

public class MovieLibrary {
    public static void main(String[] args) {
        Connection connection = ConnectionPool.getConnection();
        Logger logger = LoggerConfig.getLogger(Movie.class.getSimpleName());

        MovieDAO movieDAO = new MovieDAO();
        movieDAO.setConnection(connection);
        ActorDAO actorDAO = new ActorDAO();
        actorDAO.setConnection(connection);

        MovieRepository movieRepos = new MovieRepository(movieDAO);
        ActorRepository actorRepos = new ActorRepository(actorDAO);

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);
        MovieLibrary library = new MovieLibrary();

        //show all movies
        List<Movie> movies = movieRepos.readAll();
        logger.info("All movies at beginning: ");
        library.printMovieList(movies);
        logger.info("");

        // first task / Знайти всі фільми, що вийшли на екран у поточному та минулому році
        Calendar minDate = new GregorianCalendar();
        minDate.set(2021, Calendar.JANUARY, 1);
        final List<Movie> movieFromDate = dbDataManager.getMovieListFromProductionDate(minDate);
        logger.info("There are movies produced from " + dateFormat.format(minDate.getTime()) + " below:");
        library.printMovieList(movieFromDate);
        logger.info("");

        //second task / Вивести інформацію про акторів, які знімалися у заданому фільмі.
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

        try {
            if (!connection.isClosed()) DBConnectionCreator.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private <V> void printMovieList(List<V> vList) {
        vList.forEach(element -> logger.info(element.toString()));
    }

    private <K, V> void printMovieMapWithKeyName(Map<K, V> map, String vName) {
        map.forEach((k, v) -> logger.info(vName + " = " + v + ", " + k.toString()));
    }
}
