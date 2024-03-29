package com.sqli.stories.controller;

import com.sqli.stories.entities.TeamMember;
import com.sqli.stories.services.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TeamMemberController {
    @Autowired
    private TeamMemberService teamMemberService;

    @PostMapping("/teamMember")
    @ResponseBody
    public ResponseEntity<TeamMember> add(@RequestBody TeamMember teamMember) {
        return Optional
                .ofNullable(teamMemberService.add(teamMember))
                .map(addedTeamMember -> ResponseEntity.ok().body(addedTeamMember))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/teamMember")
    public ResponseEntity<TeamMember> updateTeam(@RequestBody TeamMember teamMember) {
        return Optional
                .ofNullable(teamMemberService.update(teamMember))
                .map(updatedTeamMember -> ResponseEntity.ok().body(updatedTeamMember))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/teamMembers")
    public ResponseEntity<List<TeamMember>> getAll() {
        return ResponseEntity.ok(teamMemberService.getAll());
    }

    @GetMapping("/teamMember/{id}")
    public ResponseEntity<TeamMember> getById(@PathVariable("id") Long id) {
        return Optional
                .ofNullable(teamMemberService.getById(id))
                .map(teamMember -> ResponseEntity.ok().body(teamMember))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/teamMember/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable("id") Long id) {
        try {
            teamMemberService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/teamMembers/team/{id}")
    public ResponseEntity<List<TeamMember>> getByTeam(@PathVariable("id") Long id){
        return ResponseEntity.ok(teamMemberService.getByTeam(id));
    }

    @GetMapping("/teamMember/member/{login}")
    public ResponseEntity<TeamMember> getTeamMemberByLogin(@PathVariable("login") String login){
        return Optional
                .ofNullable(teamMemberService.getTeamMemberByLogin(login))
                .map(teamMember -> ResponseEntity.ok().body(teamMember))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/teamMember/teams/{login}")
    public ResponseEntity<List<TeamMember>> getTeamsByLogin(@PathVariable("login") String login){
        return ResponseEntity.ok(teamMemberService.getTeamsByLogin(login));
    }
}
