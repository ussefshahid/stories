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
    public Sprint add(Sprint sprint) {
        return sprintRepository.save(sprint);
    }

    @Override
    public Sprint update(Sprint sprint) {
        return sprintRepository.save(sprint);
    }

    @Override
    public Sprint getById(Long id) {
        return sprintRepository.getOne(id);
    }

    @Override
    public List<Sprint> getAll() {
        return sprintRepository.findAll();
    }

    @Override
    public void delete(long numero) {
        sprintRepository.deleteById(numero);
    }
}
