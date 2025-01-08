package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.ProjectClass;

@Repository
public interface ProjectClassRepository extends JpaRepository<ProjectClass, Long> {
    public Optional<ProjectClass> findById(Long id);

    public void deleteById(Long id);
}
