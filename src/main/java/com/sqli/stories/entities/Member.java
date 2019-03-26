package com.sqli.stories.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "members")
public class Member implements Serializable {
    @Id
    @Size(min = 4)
    private String login;
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z ]+$",message = "first name contains only characters")
    @Size(min = 3)
    private String firstName;
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z ]+$",message = "last name contains only characters ")
    @Size(min = 3)
    private String lastName;
    private LocalDate dateEntreeProjet;
    private LocalDate dateSortieProjet;

    public Member(String login) {
        this.login = login;
    }

    public Member(String login, String firstName, String lastName, LocalDate dateEntreeProjet) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateEntreeProjet = dateEntreeProjet;
        this.dateSortieProjet = null;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(login, member.login) &&
                Objects.equals(firstName, member.firstName) &&
                Objects.equals(lastName, member.lastName) &&
                Objects.equals(dateEntreeProjet, member.dateEntreeProjet) &&
                Objects.equals(dateSortieProjet, member.dateSortieProjet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, firstName, lastName, dateEntreeProjet, dateSortieProjet);
    }
}
