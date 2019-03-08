package com.sqli.stories.entities.state;

import com.sqli.stories.entities.Story;
import com.sqli.stories.exceptions.StoryIllegalStateException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Completed")
public class CompletedState extends StoryState {
    public CompletedState(Story story) {
        super(story);
    }

    @Override
    public void inProgressState() {
        throw new StoryIllegalStateException("the story is already completed ");
    }

    @Override
    public void toDoState()
    {
        throw new StoryIllegalStateException("the story is already completed ");
    }

    @Override
    public void completedState() {
        throw new StoryIllegalStateException("the story is already completed ");
    }

    @Override
    public String toString() {
        return "Completed";
    }
}
