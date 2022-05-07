package main.ua.advanced.practice8.dao;

import main.ua.advanced.practice8.BaseDAO;
import main.ua.advanced.practice8.DBDataException;
import main.ua.advanced.practice8.IMovieDAO;
import main.ua.advanced.practice8.LoggerConfig;
import main.ua.advanced.practice8.entities.Actor;
import main.ua.advanced.practice8.entities.Movie;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;


public class MovieDAO extends BaseDAO implements IMovieDAO {
    private static final Logger logger = LoggerConfig.getLogger(MovieDAO.class.getSimpleName());

    private static final String READ_ALL = "SELECT * FROM movies AS M JOIN movie_directors ON movie_id = M.id JOIN actors AS A ON A.id = director_id";
    private static final String READ_ID = "SELECT * FROM movies AS M LEFT JOIN movie_directors ON movie_id = M.id JOIN actors AS A ON A.id = director_id WHERE M.id = ?";
    private static final String INSERT_MOVIES = "INSERT INTO movies VALUES (id, ?, ?, ?)";
    private static final String INSERT_MOVIE_DIRECTOR = "INSERT INTO movie_directors VALUES (id, ?, ?, ?)";
    private static final String UPDATE_MOVIES = "UPDATE movies SET title = ?, date_production = ?, country = ? WHERE id = ?";
    private static final String DELETE_MOVIES = "DELETE FROM movies WHERE id = ?";
    private static final String DELETE_MOVIE_DIRECTOR = "DELETE FROM movie_director WHERE movie_id = ?";
    //
    private static final String READ_OLDER_THAN_DATE = "SELECT * FROM movies AS M JOIN movie_directors ON M.id = movie_id JOIN actors AS A ON director_id = A.id WHERE M.date_production > ?";
    private static final String READ_ID_OLDER_MOVIES = "SELECT id FROM movies WHERE date_production <= ?";
    private static final String DELETE_BY_ID = "DELETE  M.*, MA.*, MD.* FROM movies AS M JOIN movie_actors AS MA ON MA.movie_id = M.id JOIN movie_directors AS MD ON MD.movie_id = M.id WHERE M.id = ?";

    @Override
    public boolean create(Movie element) {
        if (element.getDirector() == null)
            throw new NullPointerException("Movie has no director");
        boolean isCreated = false;
        try {
            PreparedStatement statement = getConnection().prepareStatement(INSERT_MOVIES);
            setMovieStatement(element, statement);
            statement.executeUpdate();
            statement = getConnection().prepareStatement(INSERT_MOVIE_DIRECTOR);
            setMovieDirectorsStatement(element, statement);
            statement.executeUpdate();
            statement.close();
            isCreated = true;
        } catch (SQLException e) {
            logger.error(LoggerConfig.exceptionMsg(e));
        }
        return isCreated;
    }

    private void setMovieDirectorsStatement(Movie element, PreparedStatement statement) throws SQLException {
        statement.setInt(1, element.getId());
        statement.setInt(2, element.getDirector().getId());
    }

    private void setMovieStatement(Movie element, PreparedStatement statement) throws SQLException {
        statement.setString(1, element.getTitle());
        statement.setDate(2, element.getProductionDate());
        statement.setString(3, element.getCountry());
    }

    @Override
    public Movie read(long id) {
        Movie movie = null;
        try (PreparedStatement statement = getConnection().prepareStatement(READ_ID)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            int objectsAmount = 0;
            while (rs.next()) {
                final Actor director = new ActorDAO().getDirectorFromResSet(rs);
                movie = getMovieFromResSet(rs, director);
                objectsAmount++;
            }
            if (objectsAmount > 1) throw new DBDataException();
        } catch (SQLException e) {
            logger.error(LoggerConfig.exceptionMsg(e));
        }
        return movie;
    }

    @Override
    public List<Movie> readAll() {
        List<Movie> movies = new LinkedList<>();
        try (Statement statement = getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(READ_ALL);
            while (rs.next()) {
                final Actor director = new ActorDAO().getDirectorFromResSet(rs);
                final Movie movie = getMovieFromResSet(rs, director);
                movies.add(movie);
            }
        } catch (SQLException e) {
            logger.error(LoggerConfig.exceptionMsg(e));
        }
        return movies;
    }

    Movie getMovieFromResSet(ResultSet rs, Actor director) throws SQLException {
        return new Movie(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("country"),
                rs.getDate("date_production"),
                director
        );
    }

    @Override
    public boolean update(Movie element) {
        boolean isUpdated = false;
        try (PreparedStatement statement = getConnection().prepareStatement(UPDATE_MOVIES)) {
            setMovieStatement(element, statement);
            statement.setInt(4, element.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(LoggerConfig.exceptionMsg(e));
        }
        return isUpdated;
    }

    @Override
    public boolean delete(Movie element) {
        if (element.getDirector() == null)
            throw new NullPointerException("Movie has no director");
        boolean isDeleted = false;
        try (PreparedStatement statement = getConnection().prepareStatement(DELETE_MOVIES)) {
            statement.setInt(1, element.getId());
            statement.executeUpdate();
            deleteMovieInMovieDirector(element);
            isDeleted = true;
        } catch (SQLException e) {
            logger.error(LoggerConfig.exceptionMsg(e));
        }
        return isDeleted;
    }

    private void deleteMovieInMovieDirector(Movie element) throws SQLException {
        try (PreparedStatement prepareStatement = getConnection().prepareStatement(DELETE_MOVIE_DIRECTOR)) {
            prepareStatement.setInt(1, element.getId());
            prepareStatement.executeUpdate();
        }
    }

    public List<Movie> getMovieListFromProductionDate(Calendar minDate) {
        Date date = new Date(minDate.getTime().getTime());
        //M.id, title, country, date_production, director_id, name
        List<Movie> movies = new LinkedList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(READ_OLDER_THAN_DATE)) {
            statement.setDate(1, date);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                final Movie movie = getMovieFromResSet(resultSet);
                movies.add(movie);
            }
        } catch (SQLException e) {
            logger.error(LoggerConfig.exceptionMsg(e));
        }
        return movies;
    }

    private Movie getMovieFromResSet(ResultSet rs) throws SQLException {
        final Actor director = new ActorDAO().getDirectorFromResSet(rs);
        return new Movie(
                rs.getInt("id"), rs.getString("title"),
                rs.getString("country"), rs.getDate("date_production"), director);
    }

    public boolean deleteMoviesOlderThan(final int maxYear) {
        boolean isDeleted = false;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -maxYear);
        Date maxDate = new Date(calendar.getTimeInMillis());
        List<Integer> movieIdForDelete = getIdMovieOlderThan(maxDate);
        try (PreparedStatement statement = getConnection().prepareStatement(DELETE_BY_ID)) {
            for (var id : movieIdForDelete) {
                statement.setInt(1, id);
                statement.addBatch();
            }
            statement.executeBatch();
            isDeleted = true;
        } catch (SQLException e) {
            logger.error(LoggerConfig.exceptionMsg(e));
        }
        return isDeleted;
    }

    private List<Integer> getIdMovieOlderThan(final Date maxDate) {
        List<Integer> ids = new LinkedList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(READ_ID_OLDER_MOVIES)) {
            statement.setDate(1, maxDate);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ids.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            logger.error(LoggerConfig.exceptionMsg(e));
        }
        return ids;
    }

}
