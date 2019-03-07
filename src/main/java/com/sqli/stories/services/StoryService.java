package com.sqli.stories.services;

import com.sqli.stories.entities.Story;

import java.util.List;

public interface StoryService {
    Story addStory(Story story);
    Story updateStoryStatus(Story story);
    List<Story> getAllStories();
    void deleteStory(long keyJira);
    void toDo(Story story);
    void inProgress(Story story);
    void completed(Story story);



}
