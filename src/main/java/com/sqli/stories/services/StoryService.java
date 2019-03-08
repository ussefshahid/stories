package com.sqli.stories.services;

import com.sqli.stories.entities.Story;

import java.util.List;

public interface StoryService {
    Story add(Story story);
    Story updateStoryStatus(Story story);
    List<Story> getAll();
    Story getByKey(Long keyJira);
    void delete(long keyJira);
    void toDo(Story story);
    void inProgress(Story story);
    void completed(Story story);



}
