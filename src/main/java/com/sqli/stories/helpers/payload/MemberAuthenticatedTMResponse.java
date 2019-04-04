package com.sqli.stories.helpers.payload;

import com.sqli.stories.entities.Team;

import java.util.Date;

/**
 * it's a helper class to return team and affectation member date
 */
public class MemberAuthenticatedTMResponse {

    private Team team;
    private Date dateEntree;

    public MemberAuthenticatedTMResponse(Team team, Date dateEntree) {
        this.team = team;
        this.dateEntree = dateEntree;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Date getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(Date dateEntree) {
        this.dateEntree = dateEntree;
    }
}
