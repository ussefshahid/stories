package com.sqli.stories.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stories")
public class Story implements Serializable {
    @Id
    private Long jiraKey;
    private String title;
    private StoryStatus status;
    private int storyPoint;
    private int priority;

    private List<Sprint> sprints;

    public Story() {
    }

    public Story(Long jiraKey, String title, StoryStatus status, int storyPoint, int priority) {
        this.jiraKey = jiraKey;
        this.title = title;
        this.status = status;
        this.storyPoint = storyPoint;
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

    public StoryStatus getStatus() {
        return status;
    }

    public void setStatus(StoryStatus status) {
        this.status = status;
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

    public List<Sprint> getSprints() {
        return sprints;
    }

    public void addSprint(Sprint sprint) {
        sprints.add(sprint);
    }

    @Override
    public String toString() {
        return "Story{" +
                "jiraKey=" + jiraKey +
                ", title='" + title + '\'' +
                '}';
    }
}

