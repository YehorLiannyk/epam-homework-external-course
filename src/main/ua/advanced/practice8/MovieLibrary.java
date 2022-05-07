package main.ua.advanced.practice8;

import main.ua.advanced.practice8.connection.ConnectionPool;
import main.ua.advanced.practice8.dao.ActorDAO;
import main.ua.advanced.practice8.dao.MovieDAO;
import main.ua.advanced.practice8.entities.Actor;
import main.ua.advanced.practice8.entities.Movie;
import main.ua.advanced.practice8.repository.ActorRepository;
import main.ua.advanced.practice8.repository.MovieRepository;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

public class MovieLibrary {
    private static final Logger logger = LoggerConfig.getLogger(Movie.class.getSimpleName());
    public static void main(String[] args) {
        Connection connection = ConnectionPool.getConnection();

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
        final List<Movie> movieFromDate = movieRepos.getDAO().getMovieListFromProductionDate(minDate);
        logger.info("There are movies produced from " + dateFormat.format(minDate.getTime()) + " below:");
        library.printMovieList(movieFromDate);
        logger.info("");

        //second task / Вивести інформацію про акторів, які знімалися у заданому фільмі.
        Movie firstMovie = movieRepos.readAll().get(0); // use for example first movie
        List<Actor> movieActors = actorRepos.getDAO().getActorListOfMovie(firstMovie);
        logger.info("Actors of movie '" + firstMovie.getTitle() + "' below: ");
        library.printMovieList(movieActors);
        logger.info("");

        //third task / Вивести інформацію про акторів, які знімалися як мінімум у N фільмах.
        final int MOVIE_AMOUNT = 2;
        Map<Actor, Integer> movieAmountActorsMap = actorRepos.getDAO().getActorsMapByMovieAmount(MOVIE_AMOUNT);
        logger.info("Actors whose movie amount >= " + MOVIE_AMOUNT + " below:");
        library.printMovieMapWithKeyName(movieAmountActorsMap, "movieAmount");
        logger.info("");

        //fourth task / Вивести інформацію про акторів, які були режисерами хоча б одного з фільмів
        final int DIRECTOR_MOVIE_AMOUNT = 1; // at least one movie
        Map<Actor, Integer> movieAmountDirectorMap = actorRepos.getDAO().getDirectorMapByMovieAmount(DIRECTOR_MOVIE_AMOUNT);
        logger.info("Actors who was a director >= " + DIRECTOR_MOVIE_AMOUNT + " times below:");
        library.printMovieMapWithKeyName(movieAmountDirectorMap, "movie director");
        logger.info("");

        //fifth task / Видалити всі фільми, дата виходу яких була більш за задане число років тому.
        final int MAX_YEAR = 10;
        logger.info("Deleting movies older than " + MAX_YEAR);
        //movieRepos.getDAO().deleteMoviesOlderThan(MAX_YEAR);
        logger.info("Movie list now: ");
        List<Movie> movieList = movieRepos.readAll();
        library.printMovieList(movieList);
    }

    private <V> void printMovieList(List<V> vList) {
        vList.forEach(element -> logger.info(element.toString()));
    }

    private <K, V> void printMovieMapWithKeyName(Map<K, V> map, String vName) {
        map.forEach((k, v) -> logger.info(vName + " = " + v + ", " + k.toString()));
    }
}
