package com.sqli.stories.controller;

import com.sqli.stories.entities.Team;
import com.sqli.stories.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @PostMapping("/team")
    public ResponseEntity<Team> add(@RequestBody Team team) {
        return Optional
                .ofNullable(teamService.add(team))
                .map(addedTeam -> ResponseEntity.ok().body(addedTeam))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @PutMapping("/team")
    public ResponseEntity<Team> update(@RequestBody Team team) {
        return Optional
                .ofNullable( teamService.update(team) )
                .map(updatedTeam -> ResponseEntity.ok().body(updatedTeam))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @GetMapping("/team/{name}")
    public ResponseEntity<Team> getByName(@PathVariable("name") String name) {
        return Optional
                .ofNullable( teamService.getByName(name) )
                .map(team -> ResponseEntity.ok().body(team))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/teams")
    public ResponseEntity<List<Team>> getAll() {
        return Optional
                .ofNullable( teamService.getAll() )
                .map(team -> ResponseEntity.ok().body(team))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/team/{id}")
    public void delete(@PathVariable("id") Long id) {
        teamService.delete(id);
    }

    @GetMapping("/team/{id}")
    public ResponseEntity<Team> getById(@PathVariable("id") Long id){
        return Optional
                .ofNullable( teamService.getById(id) )
                .map(team -> ResponseEntity.ok().body(team))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }
}
