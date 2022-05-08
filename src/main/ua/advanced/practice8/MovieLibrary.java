package main.ua.advanced.practice8;

import main.ua.advanced.practice8.entities.Actor;
import main.ua.advanced.practice8.entities.Movie;
import main.ua.advanced.practice8.repository.ActorRepository;
import main.ua.advanced.practice8.repository.MovieRepository;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

public class MovieLibrary {
    private static final Logger logger = LoggerConfig.getLogger(Movie.class.getSimpleName());

    public void showAllMovies(MovieRepository movieRepos) {
        List<Movie> movies = movieRepos.readAll();
        logger.info("All movies now: ");
        printMovieList(movies);
        logger.info("");
    }

    // first task / Знайти всі фільми, що вийшли на екран у поточному та минулому році
    public void firstTask(MovieRepository movieRepos) {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);
        Calendar minDate = new GregorianCalendar();
        minDate.set(2021, Calendar.JANUARY, 1);
        final List<Movie> movieFromDate = movieRepos.getDAO().getMovieListFromProductionDate(minDate);
        logger.info("There are movies produced from " + dateFormat.format(minDate.getTime()) + " below:");
        printMovieList(movieFromDate);
        logger.info("");
    }

    //second task / Вивести інформацію про акторів, які знімалися у заданому фільмі.
    public void secondTask(MovieRepository movieRepos, ActorRepository actorRepos) {
        Movie movie = getMovieFromInputById(movieRepos);
        List<Actor> movieActors = actorRepos.getDAO().getActorListOfMovie(movie);
        logger.info("Actors of movie '" + movie.getTitle() + "' below: ");
        printMovieList(movieActors);
        logger.info("");
    }

    private Movie getMovieFromInputById(MovieRepository movieRepos) {
        int id = Main.getInt("Input movie id to see info about its actors: ", logger);
        Movie movie = movieRepos.read(id);
        while (movie == null) {
            logger.warn("Wrong id!");
            id = Main.getInt("Input movie id to see info about its actors: ", logger);
            movie = movieRepos.read(id);
        }
        return movie;
    }

    //third task / Вивести інформацію про акторів, які знімалися як мінімум у N фільмах.
    public void thirdTask(ActorRepository actorRepos) {
        int movieAmount = Main.getInt("Input movie amount: ", logger);
        Map<Actor, Integer> movieAmountActorsMap = actorRepos.getDAO().getActorsMapByMovieAmount(movieAmount);
        if (!movieAmountActorsMap.isEmpty()) {
            logger.info("Actors whose movie amount >= " + movieAmount + " below:");
            printMovieMapWithKeyName(movieAmountActorsMap, "movieAmount");
        } else {
            logger.warn("There are no actors who played in so many movies");
        }
        logger.info("");
    }

    //fourth task / Вивести інформацію про акторів, які були режисерами хоча б одного з фільмів
    public void fourthTask(ActorRepository actorRepos) {
        final int DIRECTOR_MOVIE_AMOUNT = 1; // at least one movie
        Map<Actor, Integer> movieAmountDirectorMap = actorRepos.getDAO().getDirectorMapByMovieAmount(DIRECTOR_MOVIE_AMOUNT);
        if (!movieAmountDirectorMap.isEmpty()) {
            logger.info("Actors who was a director >= " + DIRECTOR_MOVIE_AMOUNT + " times below:");
            printMovieMapWithKeyName(movieAmountDirectorMap, "movie director");
        } else {
            logger.warn("There are no actors who directed at least one movie");
        }
        logger.info("");
    }

    //fifth task / Видалити всі фільми, дата виходу яких була більш за задане число років тому.
    public void fifthTask(MovieRepository movieRepos) {
        int maxYear = Main.getInt("Input max year value: ", logger);
        logger.info("Deleting movies older than " + maxYear);
        if (movieRepos.getDAO().deleteMoviesOlderThan(maxYear))
            logger.info("Some movies were deleted");
        else
            logger.info("There is no movie for deleting");
        logger.info("");
    }

    public <V> void printMovieList(List<V> vList) {
        vList.forEach(element -> logger.info(element.toString()));
    }

    public <K, V> void printMovieMapWithKeyName(Map<K, V> map, String vName) {
        map.forEach((k, v) -> logger.info(vName + " = " + v + ", " + k.toString()));
    }

}
