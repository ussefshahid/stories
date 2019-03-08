package com.sqli.stories.entities.factory;

import com.sqli.stories.entities.Story;
import com.sqli.stories.entities.state.CompletedState;
import com.sqli.stories.entities.state.InProgressState;
import com.sqli.stories.entities.state.StoryState;
import com.sqli.stories.entities.state.ToDoState;

public class StoryStateFactory {
    public static StoryState createInProgressStoryState(Story story){
        return new InProgressState(story);
    }
    public static StoryState createToDoStoryState(Story story){
        return new ToDoState(story);
    }
    public static StoryState createCompletedStoryState(Story story){
        return  new CompletedState(story);
    }
    private StoryStateFactory(){}
}
