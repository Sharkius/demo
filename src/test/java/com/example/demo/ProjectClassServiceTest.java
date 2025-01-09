package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.dao.impl.ProjectClassDAO;
import com.example.demo.dto.ProjectClassDTO;
import com.example.demo.entities.ProjectClass;
import com.example.demo.services.impl.ProjectClassService;

class ProjectClassServiceTest {

    @Mock
    private ProjectClassDAO projectClassDAO; // Mocked DAO layer to isolate service layer testing

    @InjectMocks
    private ProjectClassService projectClassService; // The service class being tested

    /**
     * Initializes mocks before each test method.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Automatically initializes @Mock and @InjectMocks
    }

    /**
     * Tests the creation of a new entity.
     * Verifies that the DAO layer's save method is called and the input DTO is returned as-is.
     */
    @Test
    void testCreateEntity() {
        // Prepare test data
        ProjectClassDTO projectClassDTO = new ProjectClassDTO();
        projectClassDTO.id = 1L;

        ProjectClass projectClass = new ProjectClass();
        projectClass.setId(1L);

        // Mock DAO behavior
        // Specifies to do nothing when the specified method (here -> projectClassDAO.save()) is called with any ProjectClass object
        doNothing().when(projectClassDAO).save(any(ProjectClass.class));

        // Call the service method
        ProjectClassDTO result = projectClassService.createEntity(projectClassDTO);

        // Verify interactions and result
        verify(projectClassDAO, times(1)).save(any(ProjectClass.class)); // Ensure save() was called once
        assertEquals(1L, result.id); // Check if returned DTO matches input
    }

    /**
     * Tests the deletion of an entity by ID.
     * Ensures that an exception is thrown if the entity doesn't exist.
     */
    @Test
    void testDeleteEntity() {
        // Mock an existing entity
        ProjectClass projectClass = new ProjectClass();
        projectClass.setId(1L);

        when(projectClassDAO.findById(1L)).thenReturn(projectClass); // Mock findById behavior

        // Call the service method
        assertDoesNotThrow(() -> projectClassService.deleteEntity(1L)); // Ensure no exception is thrown

        // Verify interactions
        verify(projectClassDAO, times(1)).findById(1L); // Ensure findById() was called
        verify(projectClassDAO, times(1)).delete(1L); // Ensure delete() was called
    }

    /**
     * Tests deletion when the entity does not exist.
     * Ensures that an InvalidParameterException is thrown.
     */
    @Test
    void testDeleteEntityNotFound() {
        // Mock no entity found
        when(projectClassDAO.findById(1L)).thenReturn(null);

        // Call the service method and assert exception
        assertThrows(InvalidParameterException.class, () -> projectClassService.deleteEntity(1L));

        // Verify interactions
        verify(projectClassDAO, times(1)).findById(1L); // Ensure findById() was called
        verify(projectClassDAO, never()).delete(anyLong()); // Ensure delete() was never called
    }

    /**
     * Tests retrieval of an entity by ID.
     * Ensures that the returned DTO matches the data in the DAO layer.
     */
    @Test
    void testGetEntity() {
        // Mock an existing entity
        ProjectClass projectClass = new ProjectClass();
        projectClass.setId(1L);

        when(projectClassDAO.findById(1L)).thenReturn(projectClass); // Mock findById behavior

        // Call the service method
        ProjectClassDTO result = projectClassService.getEntity(1L);

        // Verify interactions and result
        verify(projectClassDAO, times(1)).findById(1L); // Ensure findById() was called
        assertEquals(1L, result.id); // Check if the result matches the mock data
    }

    /**
     * Tests retrieval of all entities.
     * Ensures that the list of DTOs matches the data in the DAO layer.
     */
    @Test
    void testGetAll() {
        // Mock data
        ProjectClass projectClass1 = new ProjectClass();
        projectClass1.setId(1L);

        ProjectClass projectClass2 = new ProjectClass();
        projectClass2.setId(2L);

        List<ProjectClass> projectClasses = Arrays.asList(projectClass1, projectClass2);

        when(projectClassDAO.findAll()).thenReturn(projectClasses); // Mock findAll behavior

        // Call the service method
        List<ProjectClassDTO> result = projectClassService.getAll();

        // Verify interactions and result
        verify(projectClassDAO, times(1)).findAll(); // Ensure findAll() was called
        assertEquals(2, result.size()); // Check if the result list contains 2 items
        assertEquals(1L, result.get(0).id); // Check the first DTO
        assertEquals(2L, result.get(1).id); // Check the second DTO
    }

    /**
     * Tests retrieval of a non-existent entity by ID.
     * Ensures that an InvalidParameterException is thrown.
     */
    @Test
    void testGetEntityNotFound() {
        // Mock no entity found
        when(projectClassDAO.findById(1L)).thenReturn(null);

        // Call the service method and assert exception
        assertThrows(InvalidParameterException.class, () -> projectClassService.getEntity(1L));

        // Verify interactions
        verify(projectClassDAO, times(1)).findById(1L); // Ensure findById() was called
    }
}
