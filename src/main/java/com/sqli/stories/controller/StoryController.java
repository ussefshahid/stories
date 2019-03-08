package com.sqli.stories.controller;

import com.sqli.stories.entities.Story;
import com.sqli.stories.services.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StoryController {
    @Autowired
    private StoryService storyService;

    @PostMapping("/story")
    public ResponseEntity<Story> add(@RequestBody Story story) {
        return Optional
                .ofNullable(storyService.add(story))
                .map(addedStory -> ResponseEntity.ok().body(addedStory))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @PutMapping("/story")
    public ResponseEntity<Story> update(@RequestBody Story story) {
        return Optional
                .ofNullable(storyService.updateStoryStatus(story) )
                .map(updatedStory -> ResponseEntity.ok().body(updatedStory))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @GetMapping("/stories")
    public ResponseEntity<List<Story>> getAll() {
        return Optional
                .ofNullable( storyService.getAll() )
                .map(story -> ResponseEntity.ok().body(story))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/story/{id}")
    public void delete(@PathVariable("id") Long id) {
        storyService.delete(id);
    }

    @GetMapping("/story/{id}")
    public ResponseEntity<Story> getById(@PathVariable("id") Long id){
        return Optional
                .ofNullable( storyService.getByKey(id) )
                .map(story -> ResponseEntity.ok().body(story))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @PostMapping("/story/todo")
    public void toDo(@RequestBody Story story) {
        storyService.toDo(story);
    }

    @PostMapping("/story/inProgress")
    public void inProgress(@RequestBody Story story) {
        storyService.inProgress(story);
    }

    @PostMapping("/story/completed")
    public void completed(@RequestBody Story story) {
        storyService.completed(story);
    }
}
