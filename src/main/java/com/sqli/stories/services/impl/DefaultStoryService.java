package com.sqli.stories.services.impl;

import com.sqli.stories.entities.Sprint;
import com.sqli.stories.entities.Story;
import com.sqli.stories.repository.SprintRepository;
import com.sqli.stories.repository.StoryRepository;
import com.sqli.stories.services.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultStoryService implements StoryService {
    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private SprintRepository sprintRepository;

    @Override
    public Story add(Story story) {
        return storyRepository.save(story);
    }

    @Override
    public Story updateStoryStatus(Story story) {
        return storyRepository.save(story);
    }

    @Override
    public List<Story> getAll() {
        return storyRepository.findAll();
    }

    @Override
    public List<Story> searchByKeyword(String keyword) {
        return storyRepository.searchByKeyword(keyword);
    }

    @Override
    public Story getByKey(Long keyJira) {
        return storyRepository.getStoryByJiraKey(keyJira);
    }

    @Override
    public Story addSprintToStory(Long jiraKey, Sprint sprint) {
        sprintRepository.save(sprint);
        Story story=this.getByKey(jiraKey);
        story.addSprint(sprint);
        return this.add(story);
    }

    @Override
    public void delete(long keyJira) {
        storyRepository.deleteById(keyJira);
    }

}
