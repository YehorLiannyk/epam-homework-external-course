package main.ua.advanced.practice8.dao;

import main.ua.advanced.practice8.entities.Actor;

import java.util.List;

public interface IActorDAO {
    boolean create(Actor element);

    Actor read(long id);

    List<Actor> readAll();

    boolean update(Actor element);

    boolean delete(Actor element);

}
