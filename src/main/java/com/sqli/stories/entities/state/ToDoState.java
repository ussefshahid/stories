package com.sqli.stories.entities.state;

import com.sqli.stories.entities.Story;
import com.sqli.stories.entities.factory.StoryStateFactory;
import com.sqli.stories.exceptions.StoryIllegalStateException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ToDo")
public class ToDoState extends StoryState {
    public ToDoState() {
    }

    public ToDoState(Story story) {
        super(story);
    }

    @Override
    public void inProgressState() {
        story.setStoryState(StoryStateFactory.createInProgressStoryState(story));
    }

    @Override
    public void toDoState() {
        throw new StoryIllegalStateException("This story is already in todo state ");
    }

    @Override
    public void completedState() {
        story.setStoryState(StoryStateFactory.createCompletedStoryState(story));
    }

    @Override
    public String toString() {
        return "ToDo";
    }
}
