package main.ua.advanced.practice7.entities;


import main.ua.advanced.practice7.DBEntity;

import java.sql.Date;

public class Actor implements DBEntity {
    private final int id;
    private String name;
    private Date birthdate;

    public Actor(int id, String name, Date birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
