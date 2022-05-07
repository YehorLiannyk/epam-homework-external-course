package main.ua.advanced.practice8.dao;

interface MovieDefQueries {
    String READ_ALL = "SELECT M.id AS movie_id, " +
            "M.title, M.date_production, M.country, M_D.director_id, A.name, A.birthdate " +
            "FROM movies AS M " +
            "LEFT JOIN movie_directors AS M_D " +
            "ON M_D.movie_id = M.id " +
            "JOIN actors AS A " +
            "ON A.id = M_D.director_id";

    String READ_ID = "SELECT M.id AS movie_id, " +
            "M.title, M.date_production, M.country, M_D.director_id, A.name, A.birthdate " +
            "FROM movies AS M " +
            "LEFT JOIN movie_directors AS M_D " +
            "ON M_D.movie_id = M.id " +
            "JOIN actors AS A " +
            "ON A.id = M_D.director_id " +
            "WHERE M.id = ?";

    String INSERT_MOVIE = "INSERT movies (id, "

}
