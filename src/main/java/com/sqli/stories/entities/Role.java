package com.sqli.stories.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Role implements Serializable {
    @Id
    private String name;
}
