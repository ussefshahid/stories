package com.sqli.stories.controller;

import com.sqli.stories.entities.Story;
import com.sqli.stories.services.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class StoryController {
    @Autowired
    private StoryService storyService;

    @PostMapping("/story")
    public ResponseEntity<Story> add(@RequestBody Story story) {
        return Optional
                .ofNullable(storyService.add(story))
                .map(addedStory -> ResponseEntity.ok().body(addedStory))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/story")
    public ResponseEntity<Story> update(@RequestBody Story story) {
        return Optional
                .ofNullable(storyService.updateStoryStatus(story))
                .map(updatedStory -> ResponseEntity.ok().body(updatedStory))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/stories")
    public ResponseEntity<List<Story>> getAll() {
        return ResponseEntity.ok(storyService.getAll());
    }

    @DeleteMapping("/story/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try {
            storyService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/story/{id}")
    public ResponseEntity<Story> getById(@PathVariable("id") Long id) {
        return Optional
                .ofNullable(storyService.getByKey(id))
                .map(story -> ResponseEntity.ok().body(story))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
