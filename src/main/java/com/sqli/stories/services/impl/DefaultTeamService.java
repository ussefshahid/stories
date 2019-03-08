package com.sqli.stories.services.impl;

import com.sqli.stories.dao.TeamRepository;
import com.sqli.stories.entities.Team;
import com.sqli.stories.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DefaultTeamService implements TeamService {
    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Team add(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team update(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team getByName(String name) {
        return teamRepository.findByName(name);
    }

    @Override
    public Team getById(Long id) {
        return teamRepository.getOne(id);
    }

    @Override
    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    @Override
    public void delete(Long id) {
    teamRepository.deleteById(id);
    }
}
