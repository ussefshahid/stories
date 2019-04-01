package com.sqli.stories.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
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
    @OneToOne
    private Sprint sprintPLAN;
    @OneToOne
    private Sprint sprintDONE;
    @OneToOne
    private Team team;
    private int forecast;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH,CascadeType.REMOVE})
    @JoinTable(name="story_sprint",joinColumns={@JoinColumn(name="jiraKey")},
            inverseJoinColumns={@JoinColumn(name="numero")})
    private List<Sprint> sprints=new ArrayList<>();

    public Story() {
    }

    public Story(Long jiraKey, String title, int storyPoint, int priority, String storyState) {
        this.jiraKey = jiraKey;
        this.title = title;
        this.storyPoint = storyPoint;
        this.storyState = storyState;
        this.priority = priority;
        this.sprints=new ArrayList<>();
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
    public List<Sprint> getSprints() {
        return sprints;
    }

    public Sprint getSprintPLAN() {
        return sprintPLAN;
    }

    public void setSprintPLAN(Sprint sprintPLAN) {
        this.sprintPLAN = sprintPLAN;
    }

    public void setSprints(List<Sprint> sprints) {
        this.sprints = sprints;
    }
    public Sprint getCurrentSprint(){
        if(sprints!=null && !sprints.isEmpty()) {
            return this.sprints.get(sprints.size() - 1);
        }
        return null;
    }


    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
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

    public String getStoryState() {
        return storyState;
    }

    public void setStoryState(String storyState) {
        this.storyState = storyState;
    }

    public int getForecast() {
        return forecast;
    }

    public void setForecast(int forecast) {
        this.forecast = forecast;
    }

    public Sprint getSprintDONE() {
        return sprintDONE;
    }

    public void setSprintDONE(Sprint sprintDONE) {
        this.sprintDONE = sprintDONE;
    }

    public void addSprintToStory(Sprint sprint){
        sprints.add(sprint);
    }
    public void removeSprintFromStory(Sprint sprint){
        sprints.remove(sprint);
    }
    public void addSprint(Sprint sprint){
        addSprintToStory(sprint);
        sprint.addStoryToSprint(this);
    }

    public void removeSprint(Sprint sprint){
        removeSprintFromStory(sprint);
        sprint.removeStoryFromSprint(this);
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

