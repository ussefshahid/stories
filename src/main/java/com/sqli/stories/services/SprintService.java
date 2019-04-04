package com.sqli.stories.services;

import com.sqli.stories.entities.Sprint;
import com.sqli.stories.entities.Story;

import java.util.List;

public interface SprintService {
    Sprint add(Sprint sprint);
    Sprint update(Sprint sprint);
    Sprint getById(Long id);
    List<Sprint> getAll();
    boolean existsByNumero(Long numero);
    Long getBiggerExistSprintKey();
    Long getSmallestExistSprintKey();
    List<Story> getStoryBySprintKey(Long sprintKey);
    void delete(long numero);
}
