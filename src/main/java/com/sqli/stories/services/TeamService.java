package com.sqli.stories.services;

import com.sqli.stories.entities.Team;

import java.util.List;

public interface TeamService {
    Team add(Team team);
    Team update(Team team);
    Team getByName(String name);
    Team getById(Long id);
    List<Team> getAll();
    void delete(Long id);
}
