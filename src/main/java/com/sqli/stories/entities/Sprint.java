package com.sqli.stories.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Sprint implements Serializable {
    @Id
    private Long numero;
    private LocalDate dateDebut;
    private LocalDate dateFin;
}
