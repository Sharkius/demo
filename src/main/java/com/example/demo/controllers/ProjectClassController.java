package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProjectClassDTO;
import com.example.demo.services.impl.ProjectClassService;

@RestController
@RequestMapping("/entities/class")
public class ProjectClassController {

    @Autowired
    private ProjectClassService projectClassService;

    @PostMapping("/new")
    public ResponseEntity<ProjectClassDTO> createEntity(@RequestBody ProjectClassDTO projectClassDTO) {
        try {

            return ResponseEntity.ok(projectClassService.createEntity(projectClassDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable Long id) {
        try {
            projectClassService.deleteEntity(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectClassDTO> getProjectClass(@PathVariable Long id) {
        try {

            return ResponseEntity.ok(projectClassService.getEntity(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProjectClassDTO>> getAllEntities() {
        try {
            return ResponseEntity.ok(projectClassService.getAll());
        } catch (Exception e) {
            return ResponseEntity.ok(new ArrayList<ProjectClassDTO>());
        }
    }

}
