package main.ua.advanced.practice7;

import main.ua.advanced.practice7.entities.Actor;
import main.ua.advanced.practice7.entities.Movie;
import main.ua.advanced.practice8.LoggerConfig;

import java.sql.Date;
import java.sql.*;
import java.util.*;

public class DBDataManager {
    private static final String READ_ALL_MOVIES = "SELECT * FROM movies AS M JOIN movie_directors ON movie_id = M.id JOIN actors AS A ON A.id = director_id";
    private static final String READ_OLDER_THAN_DATE = "SELECT * FROM movies AS M JOIN movie_directors ON M.id = movie_id JOIN actors AS A ON director_id = A.id WHERE M.date_production > ?";
    private static final String READ_ID_OLDER_MOVIES = "SELECT id FROM movies WHERE date_production <= ?";
    private static final String DELETE_BY_ID_MOVIE = "DELETE  M.*, MA.*, MD.* FROM movies AS M JOIN movie_actors AS MA ON MA.movie_id = M.id JOIN movie_directors AS MD ON MD.movie_id = M.id WHERE M.id = ?";
    //
    private static final String READ_ACTORS_OF_MOVIE = "SELECT id, name, birthdate FROM movie_actors JOIN actors ON actor_id = id WHERE movie_id = ?";
    private static final String READ_BY_MOVIE_AMOUNT = "SELECT id, name, birthdate, COUNT(movie_id) AS movie_amount FROM movie_actors JOIN actors ON actor_id = id GROUP BY id HAVING movie_amount >= ?";
    private static final String READ_BY_DIRECTOR_MOVIE_AMOUNT = "SELECT id, name, birthdate, COUNT(movie_id) AS movie_count FROM movie_directors JOIN actors ON id = director_id GROUP BY id HAVING movie_count >= ?";

    private final Connection connection;

    public DBDataManager(Connection connection) {
        this.connection = connection;
    }

    public List<Movie> getMovieListFromProductionDate(Calendar minDate) {
        Date date = new Date(minDate.getTime().getTime());
        //M.id, title, country, date_production, director_id, name
        List<Movie> movies = new LinkedList<>();
        try (PreparedStatement statement = connection.prepareStatement(READ_OLDER_THAN_DATE)) {
            statement.setDate(1, date);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                final Movie movie = getMovieFromResSet(resultSet);
                movies.add(movie);
            }
        } catch (SQLException e) {
            MovieLibrary.logger.error(LoggerConfig.exceptionMsg(e));
        }
        return movies;
    }

    Movie getMovieFromResSet(ResultSet rs, Actor director) throws SQLException {
        Movie movie = new Movie(rs.getInt("id"), rs.getString("title"), rs.getString("country"), rs.getDate("date_production"), director);
        movie.setActors(getActorListOfMovie(movie));
        return movie;
    }

    private Movie getMovieFromResSet(ResultSet rs) throws SQLException {
        final Actor director = getDirectorFromResSet(rs);
        return getMovieFromResSet(rs, director);
    }

    private Actor getActorFromResultSet(ResultSet rs) throws SQLException {
        return new Actor(rs.getInt("id"), rs.getString("name"), rs.getDate("birthdate"));
    }

    private Actor getDirectorFromResSet(ResultSet rs) throws SQLException {
        return new Actor(rs.getInt("director_id"), rs.getString("name"), rs.getDate("birthdate"));
    }

    public List<Movie> getAllMovies() {
        List<Movie> movies = new LinkedList<>();
        try (PreparedStatement statement = connection.prepareStatement(READ_ALL_MOVIES)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                final Actor director = getDirectorFromResSet(rs);
                Movie movie = getMovieFromResSet(rs, director);
                movies.add(movie);
            }
        } catch (SQLException e) {
            MovieLibrary.logger.error(e.getMessage() + Arrays.toString(e.getStackTrace()));
        } return movies;
    }


    public List<Actor> getActorListOfMovie(Movie movie) {
        List<Actor> actors = new LinkedList<>();
        try (PreparedStatement statement = connection.prepareStatement(READ_ACTORS_OF_MOVIE)) {
            statement.setInt(1, movie.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                actors.add(getActorFromResultSet(rs));
            }
        } catch (SQLException e) {
            MovieLibrary.logger.error(e.getMessage() + Arrays.toString(e.getStackTrace()));
        }
        return actors;
    }

    public Map<Actor, Integer> getActorsMapByMovieAmount(final int movieAmount) {
        Map<Actor, Integer> actorMap = new LinkedHashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(READ_BY_MOVIE_AMOUNT)) {
            statement.setInt(1, movieAmount);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                actorMap.put(getActorFromResultSet(rs), rs.getInt("movie_amount"));
            }
        } catch (SQLException e) {
            MovieLibrary.logger.error(e.getMessage() + Arrays.toString(e.getStackTrace()));
        }
        return actorMap;
    }

    public Map<Actor, Integer> getDirectorMapByMovieAmount(final int movieAmount) {
        Map<Actor, Integer> actorMap = new LinkedHashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(READ_BY_DIRECTOR_MOVIE_AMOUNT)) {
            statement.setInt(1, movieAmount);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                actorMap.put(getActorFromResultSet(rs), rs.getInt("movie_count"));
            }
        } catch (SQLException e) {
            MovieLibrary.logger.error(e.getMessage() + Arrays.toString(e.getStackTrace()));
        }
        return actorMap;
    }

    public boolean deleteMoviesOlderThan(final int maxYear) {
        boolean isDeleted = false;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -maxYear);
        Date maxDate = new Date(calendar.getTimeInMillis());
        List<Integer> movieIdForDelete = getIdMovieOlderThan(maxDate);
        try (PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID_MOVIE)) {
            for (var id : movieIdForDelete) {
                statement.setInt(1, id);
                statement.addBatch();
            }
            statement.executeBatch();
            isDeleted = true;
        } catch (SQLException e) {
            MovieLibrary.logger.error(LoggerConfig.exceptionMsg(e));
        }
        return isDeleted;
    }

    private List<Integer> getIdMovieOlderThan(final Date maxDate) {
        List<Integer> ids = new LinkedList<>();
        try (PreparedStatement statement = connection.prepareStatement(READ_ID_OLDER_MOVIES)) {
            statement.setDate(1, maxDate);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ids.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            MovieLibrary.logger.error(LoggerConfig.exceptionMsg(e));
        }
        return ids;
    }
}
