package main.ua.advanced.practice8.entities;


import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public class Movie implements DBEntity {
    private final int id;
    private String title;
    private String country;
    private Date productionDate;
    private Actor director;
    private List<Actor> actors = new LinkedList<>();

    public Movie(int id, String title, String country, Date productionDate, Actor director) {
        this.id = id;
        this.title = title;
        this.country = country;
        this.productionDate = productionDate;
        this.director = director;
    }

    public Movie(String title, String country, Date productionDate, Actor director) {
        this(-1, title, country, productionDate, director);
    }

    @Override
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public Actor getDirector() {
        return director;
    }

    public void setDirector(Actor director) {
        this.director = director;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + title + '\'' +
                ", country='" + country + '\'' +
                ", productionDate=" + productionDate +
                ", director=" + director +
                ", actors=" + actors +
                '}';
    }
}
