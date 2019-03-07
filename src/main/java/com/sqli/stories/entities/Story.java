package com.sqli.stories.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Story implements Serializable {
    @Id
    private Long jiraKey;
    private String title;
    private Status status;
    private int storyPoint;
    private int priority;
}

enum Status {
    STARTED,
    PROCESSING,
    DONE,
}