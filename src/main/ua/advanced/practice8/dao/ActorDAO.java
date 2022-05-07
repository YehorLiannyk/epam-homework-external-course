package main.ua.advanced.practice8.dao;

import main.ua.advanced.practice8.BaseDAO;
import main.ua.advanced.practice8.DBDataException;
import main.ua.advanced.practice8.IActorDAO;
import main.ua.advanced.practice8.LoggerConfig;
import main.ua.advanced.practice8.entities.Actor;
import main.ua.advanced.practice8.entities.Movie;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ActorDAO extends BaseDAO implements IActorDAO {
    private static final Logger logger = LoggerConfig.getLogger(ActorDAO.class.getSimpleName());

    private static final String READ_ALL = "SELECT * FROM actors";
    private static final String READ_ID = "SELECT * FROM actors WHERE id = ?";
    private static final String INSERT_ACTORS = "INSERT INTO actors VALUES (id, ?, ?)";
    private static final String UPDATE_ACTOR = "UPDATE actors SET name = ?, birthdate = ? WHERE id = ?";
    private static final String DELETE_ACTOR = "DELETE FROM actors WHERE id = ?";
    //
    private static final String READ_ACTORS_OF_MOVIE = "SELECT id, name, birthdate FROM movie_actors JOIN actors ON actor_id = id WHERE movie_id = ?";
    private static final String READ_BY_MOVIE_AMOUNT = "SELECT id, name, birthdate, COUNT(movie_id) AS movie_amount FROM movie_actors JOIN actors ON actor_id = id GROUP BY id HAVING movie_amount >= ?";
    private static final String READ_BY_DIRECTOR_MOVIE_AMOUNT = "SELECT id, name, birthdate, COUNT(movie_id) AS movie_count FROM movie_directors JOIN actors ON id = director_id GROUP BY id HAVING movie_count >= ?";

    @Override
    public boolean create(Actor element) {
        if (element == null)
            throw new NullPointerException();
        boolean isCreated = false;
        try (PreparedStatement statement = getConnection().prepareStatement(INSERT_ACTORS)) {
            setActorStatement(element, statement);
            statement.executeUpdate();
            isCreated = true;
        } catch (SQLException e) {
            logger.error(LoggerConfig.exceptionMsg(e));
        }
        return isCreated;
    }

    private void setActorStatement(Actor element, PreparedStatement statement) throws SQLException {
        statement.setString(1, element.getName());
        statement.setDate(2, element.getBirthdate());
    }

    @Override
    public Actor read(long id) {
        Actor actor = null;
        try (PreparedStatement statement = getConnection().prepareStatement(READ_ID)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            int objectsAmount = 0;
            while (rs.next()) {
                actor = getActorFromResSet(rs);
                objectsAmount++;
            }
            if (objectsAmount > 1) throw new DBDataException();
        } catch (SQLException e) {
            logger.error(LoggerConfig.exceptionMsg(e));
        }
        return actor;
    }

    private Actor getActorFromResSet(ResultSet rs) throws SQLException {
        return new Actor(rs.getInt("id"), rs.getString("name"), rs.getDate("birthdate"));
    }

    @Override
    public List<Actor> readAll() {
        List<Actor> actors = new LinkedList<>();
        try (Statement statement = getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(READ_ALL);
            while (rs.next()) {
                final Actor actor = getActorFromResSet(rs);
                actors.add(actor);
            }
        } catch (SQLException e) {
            logger.error(LoggerConfig.exceptionMsg(e));
        }
        return actors;
    }

    @Override
    public boolean update(Actor element) {
        boolean isUpdated = false;
        try (PreparedStatement statement = getConnection().prepareStatement(UPDATE_ACTOR)) {
            setActorStatement(element, statement);
            statement.setInt(3, element.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(LoggerConfig.exceptionMsg(e));
        }
        return isUpdated;
    }

    @Override
    public boolean delete(Actor element) {
        if (element == null)
            throw new NullPointerException();
        boolean isDeleted = false;
        try (PreparedStatement statement = getConnection().prepareStatement(DELETE_ACTOR)) {
            statement.setInt(1, element.getId());
            statement.executeUpdate();
            isDeleted = true;
        } catch (SQLException e) {
            logger.error(LoggerConfig.exceptionMsg(e));
        }
        return isDeleted;
    }

    public Actor getDirectorFromResSet(ResultSet rs) throws SQLException {
        return new Actor(
                rs.getInt("director_id"),
                rs.getString("name"),
                rs.getDate("birthdate")
        );
    }

    public List<Actor> getActorListOfMovie(Movie movie) {
        List<Actor> actors = new LinkedList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(READ_ACTORS_OF_MOVIE)) {
            statement.setInt(1, movie.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                actors.add(getActorFromResultSet(rs));
            }
        } catch (SQLException e) {
            logger.error(LoggerConfig.exceptionMsg(e));
        }
        return actors;
    }

    private Actor getActorFromResultSet(ResultSet rs) throws SQLException {
        return new Actor(rs.getInt("id"), rs.getString("name"), rs.getDate("birthdate"));
    }

    public Map<Actor, Integer> getActorsMapByMovieAmount(final int movieAmount) {
        Map<Actor, Integer> actorMap = new LinkedHashMap<>();
        try (PreparedStatement statement = getConnection().prepareStatement(READ_BY_MOVIE_AMOUNT)) {
            statement.setInt(1, movieAmount);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                actorMap.put(getActorFromResultSet(rs), rs.getInt("movie_amount"));
            }
        } catch (SQLException e) {
            logger.error(LoggerConfig.exceptionMsg(e));
        }
        return actorMap;
    }

    public Map<Actor, Integer> getDirectorMapByMovieAmount(final int movieAmount) {
        Map<Actor, Integer> actorMap = new LinkedHashMap<>();

        try (PreparedStatement statement = getConnection().prepareStatement(READ_BY_DIRECTOR_MOVIE_AMOUNT)) {
            statement.setInt(1, movieAmount);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                actorMap.put(getActorFromResultSet(rs), rs.getInt("movie_count"));
            }
        } catch (SQLException e) {
            logger.error(LoggerConfig.exceptionMsg(e));
        }
        return actorMap;
    }
}
