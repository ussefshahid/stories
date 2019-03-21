package com.sqli.stories.controller;

import com.sqli.stories.entities.Role;
import com.sqli.stories.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*")
@RequestMapping("/api")
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
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try {
            roleService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/role/{name}")
    public ResponseEntity<List<Role>> getByName(@PathVariable("name") String name) {
        try {
            return ResponseEntity.ok(roleService.getByName(name));
        }catch (ResourceNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
    }
}
