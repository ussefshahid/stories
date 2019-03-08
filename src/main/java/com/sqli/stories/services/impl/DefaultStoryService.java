package com.sqli.stories.services.impl;

import com.sqli.stories.dao.StoryRepository;
import com.sqli.stories.entities.Story;
import com.sqli.stories.services.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultStoryService implements StoryService {
    @Autowired
    private StoryRepository storyRepository;

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
    public Story getByKey(Long keyJira) {
        return storyRepository.getOne(keyJira);
    }

    @Override
    public void delete(long keyJira) {
        storyRepository.deleteById(keyJira);
    }

    @Override
    public void toDo(Story story) {
         story.toDo();
    }

    @Override
    public void inProgress(Story story) {
        story.inProgress();
    }

    @Override
    public void completed(Story story) {
        story.completed();
    }
}
