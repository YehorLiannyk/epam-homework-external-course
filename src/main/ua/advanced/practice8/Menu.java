package main.ua.advanced.practice8;

import main.ua.advanced.practice8.repository.ActorRepository;
import main.ua.advanced.practice8.repository.MovieRepository;
import org.apache.log4j.Logger;

public class Menu {
    private static final Logger logger = LoggerConfig.getLogger(Menu.class.getSimpleName());
    private MovieRepository movieRepos;
    private ActorRepository actorRepos;
    private MovieLibrary library;

    public Menu(MovieRepository movieRepos, ActorRepository actorRepos) {
        this.movieRepos = movieRepos;
        this.actorRepos = actorRepos;
        library = new MovieLibrary();
    }

    public void start() {
        library.showAllMovies(movieRepos);
        callMenu();
    }

    private void callMenu() {

        boolean isBreak = false;
        while (!isBreak) {
            printBorder();
            logger.info("Choose the option: ");
            logger.info("1 - Знайти всі фільми, що вийшли на екран у поточному та минулому році");
            logger.info("2 - Вивести інформацію про акторів, які знімалися у заданому фільмі.");
            logger.info("3 - Вивести інформацію про акторів, які знімалися як мінімум у N фільмах");
            logger.info("4 - Вивести інформацію про акторів, які були режисерами хоча б одного з фільмів");
            logger.info("5 - Видалити всі фільми, дата виходу яких була більш за задане число років тому.");
            logger.info("6 - show all movies");
            logger.info("0 - exit");
            printBorder();

            int option = Main.getInt("Input value of menu option: ", logger);
            switch (option) {
                case 1 -> library.firstTask(movieRepos);
                case 2 -> library.secondTask(movieRepos, actorRepos);
                case 3 -> library.thirdTask(actorRepos);
                case 4 -> library.fourthTask(actorRepos);
                case 5 -> library.fifthTask(movieRepos);
                case 6 -> library.showAllMovies(movieRepos);
                case 0 -> isBreak = true;
                default -> logger.warn("Wrong option, try again");
            }
        }
    }

    private void printBorder() {
        String border = "=".repeat(40);
        logger.info(border);
    }
}
