package main.ua.advanced.practice7.entities;

import main.ua.advanced.practice7.DBEntity;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public class Movie implements DBEntity {
    private final int id;
    private String name;
    private String country;
    private Date productionDate;
    private Actor director;
    private List<Actor> actors = new LinkedList<>();

    public Movie(int id, String name, String country, Date productionDate, Actor director) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.productionDate = productionDate;
        this.director = director;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", productionDate=" + productionDate +
                ", director=" + director +
                ", actors=" + actors +
                '}';
    }
}
