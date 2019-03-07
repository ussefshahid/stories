package com.sqli.stories.services.impl;

import com.sqli.stories.dao.EquipeRepository;
import com.sqli.stories.entities.Equipe;
import com.sqli.stories.services.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DefaultEquipeService implements EquipeService {
    @Autowired
    private EquipeRepository equipeRepository;
    @Override
    public Equipe addEquipe(Equipe equipe) {
        return equipeRepository.save(equipe);
    }

    @Override
    public Equipe updateEquipe(Equipe equipe) {
        return equipeRepository.save(equipe);
    }

    @Override
    public List<Equipe> getAllEquipes() {
        List<Equipe> equipes=new ArrayList<>();
        equipeRepository.findAll().forEach(equipes::add);
        return equipes;
    }

    @Override
    public void deleteEquipe(long idEquipe) {
    equipeRepository.deleteById(idEquipe);
    }
}
