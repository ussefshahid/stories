package com.sqli.stories.services;

import com.sqli.stories.entities.Team;

import java.util.List;

public interface EquipeService {
    Team addEquipe(Team team);
    Team updateEquipe(Team team);
    List<Team> getAllEquipes();
    void deleteEquipe(long idEquipe);


}
