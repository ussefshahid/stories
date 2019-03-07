package com.sqli.stories.services;

import com.sqli.stories.entities.Sprint;

import java.util.List;

public interface SprintService {
    Sprint addSprint(Sprint sprint);
    Sprint udpateSprint(Sprint sprint);
    List<Sprint> getAllSprints();
    void deleteSprint(long numeroSprint);
}
