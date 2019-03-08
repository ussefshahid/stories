package com.sqli.stories.controller;

import com.sqli.stories.entities.Role;
import com.sqli.stories.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/role")
    public ResponseEntity<Role> add(@RequestBody Role role) {
        return Optional
                .ofNullable(roleService.add(role))
                .map(addedRole -> ResponseEntity.ok().body(addedRole))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @PutMapping("/role")
    public ResponseEntity<Role> update(@RequestBody Role role) {
        return Optional
                .ofNullable( roleService.update(role) )
                .map(updatedRole -> ResponseEntity.ok().body(updatedRole))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAll() {
        return Optional
                .ofNullable( roleService.getAll() )
                .map(role -> ResponseEntity.ok().body(role))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/role/{id}")
    public void delete(@PathVariable("id") Long id) {
        roleService.delete(id);
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<Role> getById(@PathVariable("id") Long id){
        return Optional
                .ofNullable( roleService.getById(id) )
                .map(role -> ResponseEntity.ok().body(role))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }
}
