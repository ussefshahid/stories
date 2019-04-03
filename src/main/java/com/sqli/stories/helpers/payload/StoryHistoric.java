package com.sqli.stories.helpers.payload;

import java.time.LocalDateTime;

public class StoryHistoric {
    private Long numero;
    private LocalDateTime assignementDate;
    private String team;

    public StoryHistoric(Long numero, LocalDateTime assignementDate, String team) {
        this.numero = numero;
        this.assignementDate = assignementDate;
        this.team = team;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public LocalDateTime getAssignementDate() {
        return assignementDate;
    }

    public void setAssignementDate(LocalDateTime assignementDate) {
        this.assignementDate = assignementDate;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
