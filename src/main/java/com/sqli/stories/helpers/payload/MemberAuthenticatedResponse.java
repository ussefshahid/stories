package com.sqli.stories.helpers.payload;

import com.sqli.stories.entities.Team;

import java.time.LocalDate;
import java.util.Date;

public class MemberAuthenticatedResponse {
    private String login;
    private String firstname;
    private String lastname;
    private Team team;
    private LocalDate commingDatetoSqli;
    private Date enterDateToTeam;

    public MemberAuthenticatedResponse(){}
    public MemberAuthenticatedResponse(String login, String firstname, String lastname, Team team, LocalDate commingDatetoSqli, Date enterDateToTeam) {
        this.login = login;
        this.firstname = firstname;
        this.lastname = lastname;
        this.team = team;
        this.commingDatetoSqli = commingDatetoSqli;
        this.enterDateToTeam = enterDateToTeam;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }


    public LocalDate getCommingDatetoSqli() {
        return commingDatetoSqli;
    }

    public void setCommingDatetoSqli(LocalDate commingDatetoSqli) {
        this.commingDatetoSqli = commingDatetoSqli;
    }

    public Date getEnterDateToTeam() {
        return enterDateToTeam;
    }

    public void setEnterDateToTeam(Date enterDateToTeam) {
        this.enterDateToTeam = enterDateToTeam;
    }
}
