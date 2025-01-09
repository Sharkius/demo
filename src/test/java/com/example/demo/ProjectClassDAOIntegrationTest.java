package com.example.demo;

import com.example.demo.dao.impl.ProjectClassDAO;
import com.example.demo.entities.ProjectClass;
import com.example.demo.repositories.ProjectClassRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProjectClassDAOIntegrationTest {

    @Autowired
    private ProjectClassDAO projectClassDAO;

    @Autowired
    private ProjectClassRepository projectClassRepository;

    private ProjectClass projectClass;

    @BeforeEach
    public void setUp() {
        // Create a new ProjectClass instance for testing
        projectClass = new ProjectClass();
        projectClass.setId(1L);
        projectClassRepository.save(projectClass); // Persist in DB before tests
    }

    @Test
    @Transactional
    public void testSaveAndFindById() {
        // Ensure the entity is saved and can be retrieved
        ProjectClass savedProjectClass = projectClassDAO.save(projectClass);
        ProjectClass foundProjectClass = projectClassDAO.findById(savedProjectClass.getId());
        
        assertNotNull(foundProjectClass, "ProjectClass should be found by ID");
        assertEquals(savedProjectClass.getId(), foundProjectClass.getId(), "ProjectClass ID should match");
    }

    @Test
    @Transactional
    public void testDelete() {
        // Delete the entity and check if it is removed
        projectClassDAO.delete(projectClass.getId());
        ProjectClass deletedProjectClass = projectClassDAO.findById(projectClass.getId());
        
        assertNull(deletedProjectClass, "ProjectClass should be deleted and not found");
    }

    @Test
    public void testFindAll() {
        // Fetch all entities and check that they are returned
        List<ProjectClass> projectClasses = projectClassDAO.findAll();
        assertTrue(projectClasses.size() > 0, "There should be at least one ProjectClass in the database");
    }

    
}
