package main.ua.advanced.practice8.repository;

import main.ua.advanced.practice8.DAO;
import main.ua.advanced.practice8.Repository;
import main.ua.advanced.practice8.dao.ActorDAO;
import main.ua.advanced.practice8.entities.Actor;

import java.util.List;

public class ActorRepository implements Repository<Actor> {
    private ActorDAO actorDAO;

    public ActorRepository(ActorDAO actorDAO) {
        this.actorDAO = actorDAO;
    }

    @Override
    public boolean create(Actor element) {
        return actorDAO.create(element);
    }

    @Override
    public Actor read(long id) {
        return actorDAO.read(id);
    }

    @Override
    public List<Actor> readAll() {
        return actorDAO.readAll();
    }

    @Override
    public boolean update(Actor element) {
        return actorDAO.update(element);
    }

    @Override
    public boolean delete(Actor element) {
        return actorDAO.delete(element);
    }

    @Override
    public DAO<Actor> getDAO() {
        return actorDAO;
    }
}
