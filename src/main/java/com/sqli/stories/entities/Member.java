package com.sqli.stories.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "members")
public class Member implements Serializable {
    @Id
    private String login;
    private String firstName;
    private String lastName;
    private LocalDate dateEntreeProjet;
    private LocalDate dateSortieProjet;

    public Member(String login, String firstName, String lastName, LocalDate dateEntreeProjet, LocalDate dateSortieProjet) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateEntreeProjet = dateEntreeProjet;
        this.dateSortieProjet = dateSortieProjet;
    }

    public Member() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateEntreeProjet() {
        return dateEntreeProjet;
    }

    public void setDateEntreeProjet(LocalDate dateEntreeProjet) {
        this.dateEntreeProjet = dateEntreeProjet;
    }

    public LocalDate getDateSortieProjet() {
        return dateSortieProjet;
    }

    public void setDateSortieProjet(LocalDate dateSortieProjet) {
        this.dateSortieProjet = dateSortieProjet;
    }

    @Override
    public String toString() {
        return "Member{" +
                "login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
