package main.ua.advanced.practice8.dao;

import main.ua.advanced.practice8.DAO;
import main.ua.advanced.practice8.entities.Actor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ActorDAO implements DAO<Actor> {
    private Connection connection;

    public ActorDAO(Connection connection) {
        this.connection = connection;
    }

    ActorDAO(){}

    @Override
    public boolean create(Actor element) {
        return false;
    }

    @Override
    public Actor read(long id) {
        return null;
    }

    @Override
    public List<Actor> readAll() {
        return null;
    }

    @Override
    public boolean update(Actor element) {
        return false;
    }

    @Override
    public boolean delete(Actor element) {
        return false;
    }

    public Actor getDirectorFromResSet(ResultSet rs) throws SQLException {
        return new Actor(
                rs.getInt("director_id"),
                rs.getString("name"),
                rs.getDate("birthdate")
        );
    }
}
