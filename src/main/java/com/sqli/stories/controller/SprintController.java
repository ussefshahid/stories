package com.sqli.stories.controller;

import com.sqli.stories.entities.Sprint;
import com.sqli.stories.entities.Story;
import com.sqli.stories.helpers.factory.SprintExistingKeyFactory;
import com.sqli.stories.helpers.payload.SprintExistingKey;
import com.sqli.stories.helpers.payload.SprintIdentityAvailability;
import com.sqli.stories.services.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RequestMapping("/api")
@RestController
public class SprintController {

    @Autowired
    private SprintService sprintService;

    @PostMapping("/sprint")
    public ResponseEntity<Sprint> add(@RequestBody Sprint sprint) {
        return Optional
                .ofNullable(sprintService.add(sprint))
                .map(addedSprint -> ResponseEntity.ok().body(addedSprint))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/sprint")
    public ResponseEntity<Sprint> update(@RequestBody Sprint sprint) {
        return Optional
                .ofNullable(sprintService.update(sprint))
                .map(updatedSprint -> ResponseEntity.ok().body(updatedSprint))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/sprints")
    public ResponseEntity<List<Sprint>> getAll() {
        return ResponseEntity.ok(sprintService.getAll());
    }

    @DeleteMapping("/sprint/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try {
            sprintService.delete(id);
            return ResponseEntity.noContent().build();

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/sprint/{id}")
    public ResponseEntity<Sprint> getById(@PathVariable("id") Long id) {
        return Optional
                .ofNullable(sprintService.getById(id))
                .map(sprint -> ResponseEntity.ok().body(sprint))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/sprint/checkSprintKeyAvailability")
    public ResponseEntity<SprintIdentityAvailability> checkSprintKeyAvailabilty(@RequestParam("key") Long numero){
    boolean isAvailable=!sprintService.existsByNumero(numero);
    return ResponseEntity.ok(new SprintIdentityAvailability(isAvailable));
    }

    @GetMapping("/sprint/sprintBiggerKey")
    public ResponseEntity<SprintExistingKey> getBiggerSprintKey(){
        Long key=sprintService.getBiggerExistSprintKey();
        return ResponseEntity.ok(SprintExistingKeyFactory.createSprintExistingKey(key));
    }
    @GetMapping("/sprint/sprintSmallestKey")
    public ResponseEntity<SprintExistingKey> getSmallestExistSprintKey(){
        Long key=sprintService.getSmallestExistSprintKey();
        return ResponseEntity.ok(SprintExistingKeyFactory.createSprintExistingKey(key));
    }
    @GetMapping("/sprint/{id}/stories")
    public ResponseEntity<List<Story>> getStoryBySprintKey(@PathVariable("id") Long sprintKey) {
        return ResponseEntity.ok(sprintService.getStoryBySprintKey(sprintKey));
    }
}
