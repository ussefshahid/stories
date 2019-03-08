package com.sqli.stories.controller;

import com.sqli.stories.entities.Sprint;
import com.sqli.stories.services.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SprintController {
    @Autowired
    private SprintService sprintService;

    @PostMapping("/sprint")
    public ResponseEntity<Sprint> add(@RequestBody Sprint sprint) {
        return Optional
                .ofNullable(sprintService.add(sprint))
                .map(addedSprint -> ResponseEntity.ok().body(addedSprint))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @PutMapping("/sprint")
    public ResponseEntity<Sprint> update(@RequestBody Sprint sprint) {
        return Optional
                .ofNullable( sprintService.update(sprint) )
                .map(updatedSprint -> ResponseEntity.ok().body(updatedSprint))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @GetMapping("/sprints")
    public ResponseEntity<List<Sprint>> getAll() {
        return Optional
                .ofNullable( sprintService.getAll() )
                .map(sprint -> ResponseEntity.ok().body(sprint))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/sprint/{id}")
    public void delete(@PathVariable("id") Long id) {
        sprintService.delete(id);
    }

    @GetMapping("/sprint/{id}")
    public ResponseEntity<Sprint> getById(@PathVariable("id") Long id){
        return Optional
                .ofNullable( sprintService.getById(id) )
                .map(sprint -> ResponseEntity.ok().body(sprint))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }
}
