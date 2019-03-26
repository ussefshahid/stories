package com.sqli.stories.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "stories")
public class Story implements Serializable {
    @Id
    private Long jiraKey;
    @NotBlank
    private String title;
    private int storyPoint;
    private int priority;
    private String storyState;

    @ManyToMany(mappedBy = "stories", fetch = FetchType.LAZY)
    private List<Sprint> sprints;

    public Story() {
    }

    public Story(Long jiraKey, String title, int storyPoint, int priority, String storyState) {
        this.jiraKey = jiraKey;
        this.title = title;
        this.storyPoint = storyPoint;
        this.storyState = storyState;
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

    public String getStoryState() {
        return storyState;
    }

    public void setStoryState(String storyState) {
        this.storyState = storyState;
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

