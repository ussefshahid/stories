package com.sqli.stories.entities.state;

import com.sqli.stories.entities.Story;

import javax.persistence.*;
@Entity
@Table(name="storyState")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "state",discriminatorType = DiscriminatorType.STRING,length = 20)
public abstract class StoryState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    @OneToOne
    protected Story story;

    public StoryState(Story story) {
        this.story = story;
    }
    public abstract void inProgressState();
    public abstract void toDoState();
    public abstract void completedState();
    public abstract String toString();


    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

}
