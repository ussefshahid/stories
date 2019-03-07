package com.sqli.stories.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Member implements Serializable {
    @Id
    private String login;
    private String firstName;
    private String lastName;
    private LocalDate dateEntree;
    private LocalDate dateSortie;
}
