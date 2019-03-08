package com.sqli.stories.entities;

import com.sqli.stories.entities.state.StoryState;
import com.sqli.stories.entities.factory.StoryStateFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "stories")
public class Story implements Serializable {
    @Id
    private Long jiraKey;
    private String title;
    private int storyPoint;
    private int priority;
    @OneToOne
    @JoinColumn(name = "story_state")
    private StoryState storyState;

    @ManyToMany(mappedBy = "stories", fetch = FetchType.LAZY)
    private List<Sprint> sprints;

    public Story() {
    }

    public Story(Long jiraKey, String title, int storyPoint, int priority) {
        this.jiraKey = jiraKey;
        this.title = title;
        this.storyPoint = storyPoint;
        this.storyState= StoryStateFactory.createToDoStoryState(this);
        this.priority = priority;
    }

    public Long getJiraKey() {
        return jiraKey;
    }

    public void setJiraKey(Long jiraKey) {
        this.jiraKey = jiraKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getStoryPoint() {
        return storyPoint;
    }

    public void setStoryPoint(int storyPoint) {
        this.storyPoint = storyPoint;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void addSprint(Sprint sprint) {
        sprints.add(sprint);
    }

    public StoryState getStoryState() {
        return storyState;
    }

    public void setStoryState(StoryState storyState) {
        this.storyState = storyState;
    }

    public void toDo() {
        storyState.toDoState();
    }

    public void inProgress() {
        storyState.inProgressState();
    }

    public void completed() {
        storyState.completedState();
    }

    @Override
    public String toString() {
        return "Story{" +
                "jiraKey=" + jiraKey +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Story story = (Story) o;
        return storyPoint == story.storyPoint &&
                priority == story.priority &&
                Objects.equals(jiraKey, story.jiraKey) &&
                Objects.equals(title, story.title) &&
                Objects.equals(sprints, story.sprints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jiraKey, title, storyPoint, priority, sprints);
    }

}

