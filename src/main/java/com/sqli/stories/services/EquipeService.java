package com.sqli.stories.services;

import com.sqli.stories.entities.Equipe;

import java.util.List;

public interface EquipeService {
    Equipe addEquipe(Equipe equipe);
    Equipe updateEquipe(Equipe equipe);
    List<Equipe> getAllEquipes();
    void deleteEquipe(long idEquipe);


}
