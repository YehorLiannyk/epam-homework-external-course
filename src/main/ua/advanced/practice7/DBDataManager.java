package main.ua.advanced.practice7;

import main.ua.advanced.practice7.entities.Actor;
import main.ua.advanced.practice7.entities.Movie;

import java.sql.Date;
import java.sql.*;
import java.util.*;

public class DBDataManager {
    private final Connection connection;

    public DBDataManager(Connection connection) {
        this.connection = connection;
    }

    public List<Movie> getMovieListFromProductionDate(Calendar minDate) {
        Date date = new Date(minDate.getTime().getTime());
        final String query = "SELECT m.id, title, country, date_production, director_id, name " +
                "FROM movies AS m " +
                "INNER JOIN movie_directors AS md " +
                "ON m.id = md.movie_id " +
                "INNER JOIN actors AS a " +
                "ON md.director_id = a.id " +
                "WHERE m.date_production > ?";
        List<Movie> movies = new LinkedList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, date.toString());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movie movie = createMovie(date, resultSet);
                movies.add(movie);
            }
        } catch (SQLException e) {
            MovieLibrary.logger.error(e.getMessage() + Arrays.toString(e.getStackTrace()));
        }
        return movies;
    }

    private Movie createMovie(Date date, ResultSet resultSet) throws SQLException {
        final int movie_id = resultSet.getInt("id");
        final String name = resultSet.getString("title");
        final String country = resultSet.getString("country");
        final Date prodDate = resultSet.getDate("date_production");
        final int director_id = resultSet.getInt("director_id");
        final String director_name = resultSet.getString("name");
        final Actor director = new Actor(director_id, director_name, date);
        return new Movie(movie_id, name, country, prodDate, director);
    }

    public List<Movie> getAllMovies() {
        final String query = "SELECT * " +
                "FROM movies AS m " +
                "INNER JOIN movie_directors AS md " +
                "ON m.id = md.movie_id " +
                "INNER JOIN actors AS a " +
                "ON md.director_id = a.id";
        List<Movie> movies = new LinkedList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                final Actor director = new Actor(
                        rs.getInt("director_id"),
                        rs.getString("name"),
                        rs.getDate("birthdate")
                );
                Movie movie = new Movie(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("country"),
                        rs.getDate("date_production"),
                        director
                );
                movies.add(movie);
            }
        } catch (SQLException e) {
            MovieLibrary.logger.error(e.getMessage() + Arrays.toString(e.getStackTrace()));
        }
        return movies;
    }

    public List<Actor> getActorListOfMovie(Movie movie) {
        List<Actor> actors = new LinkedList<>();
        final String query = "SELECT id, name, birthdate " +
                "FROM movie_actors AS ma " +
                "INNER JOIN actors AS a " +
                "ON ma.actor_id = a.id " +
                "WHERE ma.movie_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
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
        final String query =
                "SELECT A.id, A.name, A.birthdate, COUNT(M_A.movie_id) AS movie_amount " +
                        "FROM movie_actors AS M_A " +
                        "JOIN actors AS A ON M_A.actor_id = A.id " +
                        "GROUP BY A.id " +
                        "HAVING movie_amount >= ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, movieAmount);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                actorMap.put(
                        getActorFromResultSet(rs),
                        rs.getInt("movie_amount")
                );
            }
        } catch (SQLException e) {
            MovieLibrary.logger.error(e.getMessage() + Arrays.toString(e.getStackTrace()));
        }
        return actorMap;
    }

    public Map<Actor, Integer> getDirectorMapByMovieAmount(final int movieAmount) {
        Map<Actor, Integer> actorMap = new LinkedHashMap<>();
        final String query =
                "SELECT A.id, A.name, A.birthdate, COUNT(M_D.movie_id) AS movie_count " +
                        "FROM movie_directors AS M_D " +
                        "JOIN actors AS A " +
                        "ON A.id = M_D.director_id " +
                        "GROUP BY A.id " +
                        "HAVING movie_count >= ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, movieAmount);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                actorMap.put(
                        getActorFromResultSet(rs),
                        rs.getInt("movie_amount")
                );
            }
        } catch (SQLException e) {
            MovieLibrary.logger.error(e.getMessage() + Arrays.toString(e.getStackTrace()));
        }
        return actorMap;
    }

    private Actor getActorFromResultSet(ResultSet rs) throws SQLException {
        return new Actor(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getDate("birthdate"));
    }

}
