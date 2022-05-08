package main.ua.advanced.practice8;

import main.ua.advanced.practice8.connection.ConnectionPool;
import main.ua.advanced.practice8.dao.ActorDAO;
import main.ua.advanced.practice8.dao.MovieDAO;
import main.ua.advanced.practice8.repository.ActorRepository;
import main.ua.advanced.practice8.repository.MovieRepository;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection connection = ConnectionPool.getConnection();

        MovieDAO movieDAO = new MovieDAO();
        movieDAO.setConnection(connection);
        ActorDAO actorDAO = new ActorDAO();
        actorDAO.setConnection(connection);

        MovieRepository movieRepos = new MovieRepository(movieDAO);
        ActorRepository actorRepos = new ActorRepository(actorDAO);

        Menu menu = new Menu(movieRepos, actorRepos);
        menu.start();
    }

    public static int getInt(String msg, Logger logger) {
        Scanner sc = new Scanner(System.in);
        logger.info(msg);
        while(!sc.hasNextInt()) {
            logger.warn("Use only digits");
            logger.info(msg);
            sc.nextLine();
        }
        return sc.nextInt();
    }
}
