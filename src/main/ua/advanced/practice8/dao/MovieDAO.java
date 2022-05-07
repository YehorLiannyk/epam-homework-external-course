package main.ua.advanced.practice8.dao;

import main.ua.advanced.practice8.DAO;
import main.ua.advanced.practice8.LoggerConfig;
import main.ua.advanced.practice8.entities.Actor;
import main.ua.advanced.practice8.entities.Movie;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static main.ua.advanced.practice8.dao.MovieDefQueries.READ_ALL;
import static main.ua.advanced.practice8.dao.MovieDefQueries.READ_ID;

public class MovieDAO implements DAO<Movie> {
    private static final Logger logger = LoggerConfig.getLogger(MovieDAO.class.getSimpleName());
    Connection connection;

    public MovieDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Movie element) {
        boolean isCreated = false;

        return isCreated;
    }

    @Override
    public Movie read(long id) {
        Movie movie = null;
        try (PreparedStatement statement = connection.prepareStatement(READ_ID)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                final Actor director = new ActorDAO().getDirectorFromResSet(rs);
                movie = getMovieFromResSet(rs, director);
            }
        } catch (SQLException e) {
            logger.error(LoggerConfig.exceptionMsg(e));
        }
        return movie;
    }

    @Override
    public List<Movie> readAll() {
        List<Movie> movies = new LinkedList<>();
        try (Statement statement = connection.createStatement()) {
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
        return false;
    }

    @Override
    public boolean delete(Movie element) {
        return false;
    }

}
