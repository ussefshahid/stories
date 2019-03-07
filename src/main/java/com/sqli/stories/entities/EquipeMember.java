package com.sqli.stories.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "equipeMembers")
public class EquipeMember implements Serializable {
    @Id
    private Equipe equipe;
    @EmbeddedId
    private Member member;
    @EmbeddedId
    private Role role;
    private LocalDate dateEntree;
    private LocalDate dateSortie;

    public EquipeMember(Equipe equipe, Member member, Role role, LocalDate dateEntree, LocalDate dateSortie) {
        this.equipe = equipe;
        this.member = member;
        this.role = role;
        this.dateEntree = dateEntree;
        this.dateSortie = dateSortie;
    }

    public EquipeMember() {
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDate getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(LocalDate dateEntree) {
        this.dateEntree = dateEntree;
    }

    public LocalDate getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(LocalDate dateSortie) {
        this.dateSortie = dateSortie;
    }

    @Override
    public String toString() {
        return "EquipeMember{" +
                "equipe=" + equipe +
                ", member=" + member +
                ", role=" + role +
                '}';
    }
}
