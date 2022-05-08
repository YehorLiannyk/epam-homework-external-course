package main.ua.advanced.practice8.dao;

import main.ua.advanced.practice8.entities.Movie;

import java.util.List;

public interface IMovieDAO {
    boolean create(Movie element);
    Movie read(long id);
    List<Movie> readAll();
    boolean update(Movie element);
    boolean delete(Movie element);

}
