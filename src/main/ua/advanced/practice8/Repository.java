package main.ua.advanced.practice8;

import java.util.List;

public interface Repository<T> {
    boolean create(T element);
    T read(long id);
    List<T> readAll();
    boolean update(T element);
    boolean delete(T element);
}
