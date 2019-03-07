package com.sqli.stories.services.impl;

import com.sqli.stories.dao.SprintRepository;
import com.sqli.stories.entities.Sprint;
import com.sqli.stories.services.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DefaultSprintService implements SprintService {

    @Autowired
    private SprintRepository sprintRepository;
    @Override
    public Sprint addSprint(Sprint sprint) {
        return sprintRepository.save(sprint);
    }

    @Override
    public Sprint udpateSprint(Sprint sprint) {
        return sprintRepository.save(sprint);
    }

    @Override
    public List<Sprint> getAllSprints() {
        List<Sprint> sprints=new ArrayList<>();
        sprintRepository.findAll().forEach(sprints::add);
        return sprints;
    }

    @Override
    public void deleteSprint(long numeroSprint) {
        sprintRepository.deleteById(numeroSprint);
    }
}
