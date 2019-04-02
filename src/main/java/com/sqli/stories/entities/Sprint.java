package com.sqli.stories.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "sprints")
public class Sprint implements Serializable {
    @Id
    private Long numero;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    public Sprint(Long numero, LocalDate dateDebut, LocalDate dateFin) {
        this.numero = numero;
        this.dateDebut=dateDebut;
        this.dateFin = dateFin;
    }

    public Sprint() {
        this.dateDebut=LocalDate.now();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sprint sprint = (Sprint) o;
        return Objects.equals(numero, sprint.numero) &&
                Objects.equals(dateDebut, sprint.dateDebut) &&
                Objects.equals(dateFin, sprint.dateFin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, dateDebut, dateFin);
    }
}
