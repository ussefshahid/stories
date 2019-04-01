package com.sqli.stories.services.impl;

import com.sqli.stories.entities.Sprint;
import com.sqli.stories.entities.Story;
import com.sqli.stories.repository.SprintRepository;
import com.sqli.stories.repository.StoryRepository;
import com.sqli.stories.services.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultSprintService implements SprintService {
    @Autowired
    private SprintRepository sprintRepository;
    @Autowired
    private StoryRepository storyRepository;

    @Override
    public Sprint add(Sprint sprint) {
        return sprintRepository.save(sprint);
    }

    @Override
    public Sprint update(Sprint sprint) {
        return sprintRepository.save(sprint);
    }

    @Override
    public Sprint getById(Long numero) {
        return sprintRepository.getSprintByNumero(numero);
    }

    @Override
    public List<Sprint> getAll() {
        return sprintRepository.findAll();
    }

    @Override
    public boolean existsByNumero(Long numero) {
        return sprintRepository.existsByNumero(numero);
    }

    @Override
    public Long getBiggerExistSprintKey() {
        return sprintRepository.getBiggerExistSprintKey();
    }

    @Override
    public List<Story> getStoryBySprintKey(Long sprintKey) {
        List<Story> stories;
        List<Story> listStories=storyRepository.findAll();
        stories= listStories.stream().filter(e->e.getCurrentSprint()!=null && e.getCurrentSprint().getNumero()==sprintKey).collect(Collectors.toList());
        return stories;
    }

    @Override
    public void delete(long numero) {
        sprintRepository.deleteById(numero);
    }
}
