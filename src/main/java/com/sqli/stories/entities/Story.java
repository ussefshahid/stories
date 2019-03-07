package com.sqli.stories.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@Entity
public class Story implements Serializable {
    @Id
    private Long jiraKey;
    private String title;
    private Status status;
    private int storyPoint;
    private int priority;

    private List<Sprint> sprints;
}

enum Status {
    STARTED,
    PROCESSING,
    DONE,
}