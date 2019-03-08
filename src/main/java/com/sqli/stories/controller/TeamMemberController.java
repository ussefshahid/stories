package com.sqli.stories.controller;

import com.sqli.stories.entities.TeamMember;
import com.sqli.stories.services.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TeamMemberController {
    @Autowired
    private TeamMemberService teamMemberService;

    @PostMapping("/teamMember")
    public ResponseEntity<TeamMember> add(@RequestBody TeamMember teamMember) {
        return Optional
                .ofNullable(teamMemberService.add(teamMember))
                .map(addedTeamMember -> ResponseEntity.ok().body(addedTeamMember))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }
    @PutMapping("/teamMember")
    public ResponseEntity<TeamMember> updateTeam(@RequestBody TeamMember teamMember) {
        return Optional
                .ofNullable(teamMemberService.update(teamMember))
                .map(updatedTeamMember -> ResponseEntity.ok().body(updatedTeamMember))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @GetMapping("/teamMembers")
    public ResponseEntity<List<TeamMember>> getAll() {
        return Optional
                .ofNullable(teamMemberService.getAll())
                .map(teamMember -> ResponseEntity.ok().body(teamMember))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @GetMapping("/teamMember/{id}")
    public ResponseEntity<TeamMember> getById(@PathVariable("id") Long id){
        return Optional
                .ofNullable( teamMemberService.getById(id) )
                .map(teamMember -> ResponseEntity.ok().body(teamMember))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @DeleteMapping("/teamMember/{id}")
    public void deleteTeam(@PathVariable("id") Long id) {
        teamMemberService.delete(id);
    }
}
