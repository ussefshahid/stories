package com.sqli.stories.helpers.factory;

import com.sqli.stories.entities.Sprint;
import com.sqli.stories.entities.Story;
import com.sqli.stories.entities.StorySprint;
import com.sqli.stories.helpers.payload.StoryHistoric;

import java.time.LocalDateTime;

public class SprintStoryFactory {
    private SprintStoryFactory(){}
    public static StorySprint createStorySprint(Sprint sprint, Story story) {
        return new StorySprint(story, sprint);
    }
    public static StoryHistoric createStoryHistoric(Long numero , LocalDateTime assignementDate,String team){
        return new StoryHistoric(numero,assignementDate,team);
    }
}
