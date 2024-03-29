package com.sqli.stories.services.impl;

import com.sqli.stories.entities.Sprint;
import com.sqli.stories.entities.Story;
import com.sqli.stories.repository.SprintRepository;
import com.sqli.stories.repository.StoryRepository;
import com.sqli.stories.services.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Long getSmallestExistSprintKey() {
        return sprintRepository.getSmallestExistSprintKey();
    }

    @Override
    public List<Story> getStoryBySprintKey(Long sprintKey) {
        return storyRepository.getStoriesBySprintKey(sprintKey);
    }

    @Override
    public void delete(long numero) {
        sprintRepository.deleteById(numero);
    }
}
