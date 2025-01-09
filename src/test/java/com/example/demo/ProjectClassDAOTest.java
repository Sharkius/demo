package com.example.demo;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.dao.impl.ProjectClassDAO;
import com.example.demo.entities.ProjectClass;
import com.example.demo.repositories.ProjectClassRepository;

class ProjectClassDAOTest {

    // Mock the ProjectClassRepository to isolate the DAO layer from the actual database
    @Mock
    private ProjectClassRepository projectClassRepository;

    // Inject the mocked repository into the DAO being tested
    @InjectMocks
    private ProjectClassDAO projectClassDAO;

    // Set up the test environment before each test
    @BeforeEach
    void setUp() {
        // Initialize mocks annotated with @Mock and @InjectMocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        // Arrange: Create a new ProjectClass object to simulate the input
        ProjectClass projectClass = new ProjectClass();
        projectClass.setId(1L); // Set an ID for the object

        // Stub the repository's save method to return the input object when called
        when(projectClassRepository.save(any(ProjectClass.class))).thenReturn(projectClass);

        // Act: Call the DAO's save method
        ProjectClass savedProjectClass = projectClassDAO.save(projectClass);

        // Assert: Verify that the returned object is not null and matches the input's ID
        assertNotNull(savedProjectClass); // Ensure the result is not null
        assertEquals(1L, savedProjectClass.getId()); // Check that the ID matches

        // Verify: Ensure that the repository's save method was called exactly once
        verify(projectClassRepository, times(1)).save(any(ProjectClass.class));
    }

    @Test
    void testDelete() {
        // Act: Call the DAO's delete method with a specific ID
        projectClassDAO.delete(1L);

        // Verify: Ensure that the repository's deleteById method was called with the correct ID
        verify(projectClassRepository, times(1)).deleteById(1L);
    }

    @Test
    void testFindById() {
        // Arrange: Create a ProjectClass object to simulate a database record
        ProjectClass projectClass = new ProjectClass();
        projectClass.setId(1L); // Set an ID for the object

        // Stub the repository's findById method to return an Optional containing the object
        when(projectClassRepository.findById(1L)).thenReturn(Optional.of(projectClass));

        // Act: Call the DAO's findById method
        ProjectClass foundProjectClass = projectClassDAO.findById(1L);

        // Assert: Verify that the returned object is not null and matches the expected ID
        assertNotNull(foundProjectClass); // Ensure the result is not null
        assertEquals(1L, foundProjectClass.getId()); // Check that the ID matches

        // Verify: Ensure that the repository's findById method was called with the correct ID
        verify(projectClassRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdNotFound() {
        // Arrange: Stub the repository's findById method to return an empty Optional
        when(projectClassRepository.findById(1L)).thenReturn(Optional.empty());

        // Act: Call the DAO's findById method
        ProjectClass foundProjectClass = projectClassDAO.findById(1L);

        // Assert: Verify that the returned object is null (no record found)
        assertNull(foundProjectClass);

        // Verify: Ensure that the repository's findById method was called with the correct ID
        verify(projectClassRepository, times(1)).findById(1L);
    }

    @Test
    void testFindAll() {
        // Arrange: Create a list of ProjectClass objects to simulate multiple database records
        ProjectClass projectClass1 = new ProjectClass();
        projectClass1.setId(1L); // Set an ID for the first object

        ProjectClass projectClass2 = new ProjectClass();
        projectClass2.setId(2L); // Set an ID for the second object

        List<ProjectClass> projectClasses = Arrays.asList(projectClass1, projectClass2); // Combine them into a list

        // Stub the repository's findAll method to return the list of objects
        when(projectClassRepository.findAll()).thenReturn(projectClasses);

        // Act: Call the DAO's findAll method
        List<ProjectClass> foundProjectClasses = projectClassDAO.findAll();

        // Assert: Verify that the returned list is not null and has the expected size and content
        assertNotNull(foundProjectClasses); // Ensure the result is not null
        assertEquals(2, foundProjectClasses.size()); // Check that the list has the correct size
        assertEquals(1L, foundProjectClasses.get(0).getId()); // Check the ID of the first object
        assertEquals(2L, foundProjectClasses.get(1).getId()); // Check the ID of the second object

        // Verify: Ensure that the repository's findAll method was called exactly once
        verify(projectClassRepository, times(1)).findAll();
    }
}
