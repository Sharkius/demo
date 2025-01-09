package com.example.demo;

import com.example.demo.dao.impl.ProjectClassDAO;
import com.example.demo.dto.ProjectClassDTO;
import com.example.demo.entities.ProjectClass;
import com.example.demo.services.impl.ProjectClassService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProjectClassServiceIntegrationTest {

    @Autowired
    private ProjectClassService projectClassService;

    @Autowired
    private ProjectClassDAO projectClassDAO;

    private ProjectClassDTO projectClassDTO;

    @BeforeEach
    public void setUp() {
        // Initialize a ProjectClassDTO object
        projectClassDTO = new ProjectClassDTO();
        projectClassDTO.id = 1L; // Set the ID
    }

    @Test
    @Transactional
    public void testCreateEntity() {
        // Call the service method to create the entity
        ProjectClassDTO createdDTO = projectClassService.createEntity(projectClassDTO);

        // Verify that the entity was saved
        assertNotNull(createdDTO, "Created ProjectClassDTO should not be null");
        assertEquals(projectClassDTO.id, createdDTO.id, "ProjectClassDTO ID should match");
    }

    @Test
    @Transactional
    public void testGetEntity() {
        // Save the entity to the database
        ProjectClass projectClass = new ProjectClass();
        projectClass.setId(1L);
        projectClassDAO.save(projectClass);

        // Fetch the entity using the service
        ProjectClassDTO foundDTO = projectClassService.getEntity(1L);
        
        // Verify that the fetched DTO matches the saved entity
        assertNotNull(foundDTO, "ProjectClassDTO should be found by ID");
        assertEquals(1L, foundDTO.id, "The fetched ID should match the saved entity's ID");
    }
}
