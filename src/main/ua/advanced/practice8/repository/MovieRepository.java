package main.ua.advanced.practice8.repository;

import main.ua.advanced.practice8.DAO;
import main.ua.advanced.practice8.Repository;
import main.ua.advanced.practice8.dao.MovieDAO;
import main.ua.advanced.practice8.entities.Movie;

import java.util.List;

public class MovieRepository implements Repository<Movie> {
    private MovieDAO movieDAO;

    public MovieRepository(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }

    @Override
    public boolean create(Movie element) {
        return movieDAO.create(element);
    }

    @Override
    public Movie read(long id) {
        return movieDAO.read(id);
    }

    @Override
    public List<Movie> readAll() {
        return movieDAO.readAll();
    }

    @Override
    public boolean update(Movie element) {
        return movieDAO.update(element);
    }

    @Override
    public boolean delete(Movie element) {
        return delete(element);
    }

    @Override
    public DAO<Movie> getDAO() {
        return movieDAO;
    }
}
