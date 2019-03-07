package com.sqli.stories.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "equipes")
public class Equipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public Equipe(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Equipe{" +
                "name='" + name + '\'' +
                '}';
    }
}
