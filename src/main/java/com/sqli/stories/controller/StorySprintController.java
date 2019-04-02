package com.sqli.stories.controller;

import com.sqli.stories.entities.StorySprint;
import com.sqli.stories.helpers.payload.StoryHistoric;
import com.sqli.stories.services.StorySprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/api")
@RestController
public class StorySprintController {
    @Autowired
    private StorySprintService storySprintService;
    @PostMapping("/storySprint")
    public ResponseEntity<StorySprint> addStorySprint(@RequestBody  StorySprint storySprint) {
       return ResponseEntity.ok(storySprintService.addStorySprint(storySprint));
    }

    @GetMapping("/storySprint/{id}/sprints")
    public ResponseEntity<List<StoryHistoric>> getSprintsByStoryKey(@PathVariable("id") Long jiraKey) {
        try {
          return ResponseEntity.ok(storySprintService.getSprintsByStoryKey(jiraKey));
        }catch (ResourceNotFoundException ex){
            return ResponseEntity.noContent().build();
        }
    }
}
