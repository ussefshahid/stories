package com.sqli.stories.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Equipe implements Serializable {
    @Id
    private String name;
}
