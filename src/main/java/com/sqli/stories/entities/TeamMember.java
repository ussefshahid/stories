package com.sqli.stories.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
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
    @Temporal(TemporalType.DATE)
    private Date dateEntree;
    @Temporal(TemporalType.DATE)
    private Date dateSortie;

    public TeamMember(Team team, Member member, Role role) {
        this.team = team;
        this.member = member;
        this.role = role;
    }

    public TeamMember(Team team, Member member, Role role, Date dateEntree, Date dateSortie) {
        this.team = team;
        this.member = member;
        this.role = role;
        this.dateEntree = dateEntree;
        this.dateSortie = dateSortie;
    }

    public TeamMember() {
    }

    public Long getId() {
        return id;
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

    public Date getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(Date dateEntree) {
        this.dateEntree = dateEntree;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    @Override
    public String toString() {
        return "TeamMember{" +
                "id=" + id +
                ", team=" + team +
                ", member=" + member +
                ", role=" + role +
                ", dateEntree=" + dateEntree +
                ", dateSortie=" + dateSortie +
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
