package com.sqli.stories.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "story_sprint")
@Entity
public class StorySprint implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "jiraKey")
    private Story story;
    @OneToOne
    @JoinColumn(name = "numero")
    private Sprint sprint;
    @Column(name = "assignementDate")
    private LocalDateTime assignementDate;

    public StorySprint(Story story, Sprint sprint) {
        this.story = story;
        this.sprint = sprint;
        this.assignementDate = LocalDateTime.now();
    }
    public StorySprint(){}

    public Long getId() {
        return id;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public LocalDateTime getAssignementDate() {
        return assignementDate;
    }

    public void setAssignementDate(LocalDateTime assignementDate) {
        this.assignementDate = assignementDate;
    }
}
