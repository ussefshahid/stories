package com.sqli.stories.services.impl;

import com.sqli.stories.dao.StoryRepository;
import com.sqli.stories.entities.Story;
import com.sqli.stories.services.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultStoryService implements StoryService {

    @Autowired
    private StoryRepository storyRepository;

    @Override
    public Story addStory(Story story) {
        return storyRepository.save(story);
    }

    @Override
    public Story updateStoryStatus(Story story) {
        return storyRepository.save(story);
    }

    @Override
    public List<Story> getAllStories() {
        List<Story> stories = new ArrayList<>();
        storyRepository.findAll().forEach(stories::add);
        return stories;
    }

    @Override
    public void deleteStory(long keyJira) {
        storyRepository.deleteById(keyJira);
    }

    @Override
    public void toDo(Story story) {
         story.getStoryState().toDoState();
    }

    @Override
    public void inProgress(Story story) {
        story.getStoryState().inProgressState();
    }

    @Override
    public void completed(Story story) {
        story.getStoryState().completedState();
    }
}
