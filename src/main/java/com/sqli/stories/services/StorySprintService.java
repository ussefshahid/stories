package com.sqli.stories.services;

import com.sqli.stories.entities.StorySprint;
import com.sqli.stories.helpers.payload.StoryHistoric;

import java.util.List;

public interface StorySprintService {
    StorySprint addStorySprint(StorySprint storySprint);
    List<StoryHistoric> getSprintsByStoryKey(Long jiraKey);
}
