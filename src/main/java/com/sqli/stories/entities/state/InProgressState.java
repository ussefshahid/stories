package com.sqli.stories.entities.state;

import com.sqli.stories.entities.Story;
import com.sqli.stories.entities.stateFactory.StoryStateFactory;
import com.sqli.stories.exceptions.StoryIllegalStateException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("InProgress")
public class InProgressState extends StoryState {

    public InProgressState(Story story) {
        super(story);
    }

    @Override
    public void inProgressState() {
        throw new StoryIllegalStateException("the story is already in progress state  ");
    }

    @Override
    public void toDoState() {
     story.setStoryState(StoryStateFactory.createToDoStoryState(story));
    }

    @Override
    public void completedState() {
        story.setStoryState(StoryStateFactory.createCompletedStoryState(story));

    }
}
