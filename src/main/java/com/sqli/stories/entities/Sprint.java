package com.sqli.stories.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "sprints")
public class Sprint implements Serializable {
    @Id
    private Long numero;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    @ManyToMany
    @JoinTable(name="Sprint_Story",joinColumns=@JoinColumn(name="numero"),
            inverseJoinColumns=@JoinColumn(name="jiraKey"))
    private List<Story> stories;

    public Sprint(Long numero, LocalDate dateDebut, LocalDate dateFin) {
        this.numero = numero;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Sprint() {
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "Sprint{" +
                "numero=" + numero +
                '}';
    }
}
