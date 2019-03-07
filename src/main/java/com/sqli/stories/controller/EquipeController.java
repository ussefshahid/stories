package com.sqli.stories.controller;

import com.sqli.stories.entities.Equipe;
import com.sqli.stories.services.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
@CrossOrigin(origins = "http://localhost:3000")
public class EquipeController {

    @Autowired
    private EquipeService equipeService;

    @PostMapping("/equipe")
    public ResponseEntity<Equipe> addEquipe(@RequestBody Equipe equipe) {
        return ResponseEntity.ok(equipeService.addEquipe(equipe));
    }

    @PutMapping("/equipe")
    public ResponseEntity<Equipe> updateEquipe(@RequestBody Equipe equipe) {
        return ResponseEntity.ok(equipeService.addEquipe(equipe));
    }

    @GetMapping("/equipe")
    public ResponseEntity<List<Equipe>> getAllEquipes() {
        return ResponseEntity.ok(equipeService.getAllEquipes());
    }

    @DeleteMapping("/equipe/{id}")
    public ResponseEntity<Void> deleteEquipe(@PathVariable(value = "id", required = true) long idEquipe) {
        try {
            equipeService.deleteEquipe(idEquipe);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }


}
