package com.sqli.stories.services;

import com.sqli.stories.entities.Sprint;

import java.util.List;

public interface SprintService {
    Sprint add(Sprint sprint);
    Sprint update(Sprint sprint);
    Sprint getById(Long id);
    List<Sprint> getAll();
    boolean existsByNumero(Long numero);
    Long getBiggerExistSprintKey();
    void delete(long numero);
}
