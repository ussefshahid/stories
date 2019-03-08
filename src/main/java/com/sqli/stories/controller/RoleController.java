package com.sqli.stories.controller;

import com.sqli.stories.entities.Role;
import com.sqli.stories.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/role")
    public ResponseEntity<Role> add(@RequestBody Role role) {
        return Optional
                .ofNullable(roleService.add(role))
                .map(addedRole -> ResponseEntity.ok().body(addedRole))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/role")
    public ResponseEntity<Role> update(@RequestBody Role role) {
        return Optional
                .ofNullable(roleService.update(role))
                .map(updatedRole -> ResponseEntity.ok().body(updatedRole))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAll() {
        return ResponseEntity.ok(roleService.getAll());
    }

    @DeleteMapping("/role/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String name) {
        try {
            roleService.delete(name);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<List<Role>> getById(@PathVariable("id") String name) {
        return ResponseEntity.ok(roleService.getById(name));
    }
}
