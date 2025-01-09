package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.entities.ProjectClass;
import com.example.demo.repositories.ProjectClassRepository;

// Annotate the class to set up a test environment for JPA repositories
@DataJpaTest
class ProjectClassRepositoryTest {

    // Automatically wire the repository for testing
    @Autowired
    private ProjectClassRepository projectClassRepository;

    private ProjectClass projectClass;

    @BeforeEach
    void setUp() {
        // Initialize a ProjectClass entity to be used in the tests
        projectClass = new ProjectClass();
        projectClass.setId(1L); // Set an ID for the entity
    }

    @Test
    void testSave() {
        // Act: Save the ProjectClass entity into the in-memory database
        ProjectClass savedProjectClass = projectClassRepository.save(projectClass);

        // Assert: Ensure the entity is saved and the returned object is not null
        assertNotNull(savedProjectClass); // Check that the saved object is not null
        assertEquals(1L, savedProjectClass.getId()); // Verify the ID matches the input
    }

    @Test
    void testFindById() {
        // Arrange: Save a ProjectClass entity to the database to make it retrievable
        projectClassRepository.save(projectClass);

        // Act: Attempt to retrieve the entity using its ID
        Optional<ProjectClass> retrievedProjectClass = projectClassRepository.findById(1L);

        // Assert: Verify the retrieved entity is present and matches the saved data
        assertTrue(retrievedProjectClass.isPresent()); // Ensure the entity is found
        assertEquals(1L, retrievedProjectClass.get().getId()); // Check that the ID matches
    }

    @Test
    void testFindByIdNotFound() {
        // Act: Try to find an entity by an ID that does not exist
        Optional<ProjectClass> retrievedProjectClass = projectClassRepository.findById(99L);

        // Assert: Verify that the retrieved entity is not present
        assertFalse(retrievedProjectClass.isPresent()); // Ensure no entity is found
    }

    @Test
    void testDeleteById() {
        // Arrange: Save a ProjectClass entity so it can be deleted
        projectClassRepository.save(projectClass);

        // Act: Delete the entity by its ID
        projectClassRepository.deleteById(1L);

        // Assert: Attempt to retrieve the deleted entity and ensure it is no longer present
        Optional<ProjectClass> retrievedProjectClass = projectClassRepository.findById(1L);
        assertFalse(retrievedProjectClass.isPresent()); // Ensure the entity is deleted
    }

    @Test
    void testFindAll() {
        // Arrange: Save multiple ProjectClass entities to the database
        ProjectClass projectClass2 = new ProjectClass();
        projectClass2.setId(2L);

        projectClassRepository.save(projectClass); // Save the first entity
        projectClassRepository.save(projectClass2); // Save the second entity

        // Act: Retrieve all entities from the repository
        Iterable<ProjectClass> projectClasses = projectClassRepository.findAll();

        // Assert: Verify that the number of retrieved entities matches the number of saved entities
        assertNotNull(projectClasses); // Ensure the result is not null
        assertEquals(2, ((List<ProjectClass>) projectClasses).size()); // Check the size of the result
    }
}
