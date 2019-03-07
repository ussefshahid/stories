package com.sqli.stories.services;

import com.sqli.stories.entities.Story;

public interface StoryService {
    Story addStory(Story story);
    Story updateStoryStatus();

}
