package com.sqli.stories.services;

import com.sqli.stories.entities.Sprint;
import com.sqli.stories.entities.Story;

import java.util.List;

public interface StoryService {
    Story add(Story story);
    Story updateStoryStatus(Story story);
    List<Story> getAll();
    List<Story> searchByKeyword(String keyword);
    Story getByKey(Long keyJira);
    Story addSprintToStory(Long jiraKey, Sprint sprint);
    void delete(long keyJira);
}
