package com.sqli.stories.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "teamMembers")
public class TeamMember implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="TEAM_ID")
    private Team team;
    @OneToOne
    private Member member;
    @OneToOne
    private Role role;
    private LocalDate dateEntree;
    private LocalDate dateSortie;

    public TeamMember(Team team, Member member, Role role) {
        this.team = team;
        this.member = member;
        this.role = role;
    }

    public TeamMember(Team team, Member member, Role role, LocalDate dateEntree, LocalDate dateSortie) {
        this.team = team;
        this.member = member;
        this.role = role;
        this.dateEntree = dateEntree;
        this.dateSortie = dateSortie;
    }

    public TeamMember() {
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
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
        return "TeamMember{" +
                "team=" + team +
                ", member=" + member +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamMember that = (TeamMember) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(team, that.team) &&
                Objects.equals(member, that.member) &&
                Objects.equals(role, that.role) &&
                Objects.equals(dateEntree, that.dateEntree) &&
                Objects.equals(dateSortie, that.dateSortie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, team, member, role, dateEntree, dateSortie);
    }
}
