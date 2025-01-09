package com.example.demo;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controllers.ProjectClassController;
import com.example.demo.dto.ProjectClassDTO;
import com.example.demo.services.impl.ProjectClassService;
import com.fasterxml.jackson.databind.ObjectMapper;

class ProjectClassControllerTest {

    private MockMvc mockMvc; // Used to perform mock HTTP requests to the controller

    @Mock
    private ProjectClassService projectClassService; // Mocked service layer to isolate controller testing

    @InjectMocks
    private ProjectClassController projectClassController; // The controller being tested

    private ObjectMapper objectMapper; // Utility to convert objects to JSON for request payloads

    /**
     * Sets up the test environment before each test.
     * Initializes mocks, configures MockMvc, and creates an ObjectMapper instance.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(projectClassController).build();
        objectMapper = new ObjectMapper();
    }

    /**
     * Tests the creation of a new entity through the POST /entities/class/new endpoint.
     * Verifies the returned status code, JSON response structure, and service method invocation.
     */
    @Test
    void testCreateEntity() throws Exception {
        // Mock request payload
        ProjectClassDTO requestDTO = new ProjectClassDTO();
        requestDTO.setId(1L);

        // Mock service layer response
        when(projectClassService.createEntity(any(ProjectClassDTO.class))).thenReturn(requestDTO);

        // Perform POST request and verify response
        mockMvc.perform(post("/entities/class/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk()) // Assert HTTP 200 status
                .andExpect(jsonPath("$.id").value(1L)); // Assert returned JSON contains id = 1

        // Verify service method was called once
        verify(projectClassService, times(1)).createEntity(any(ProjectClassDTO.class));
    }

    /**
     * Tests the deletion of an entity through the DELETE /entities/class/delete/{id} endpoint.
     * Verifies the returned status code and service method invocation.
     */
    @Test
    void testDeleteEntity() throws Exception {
        // Mock service layer method with no action (void)
        doNothing().when(projectClassService).deleteEntity(1L);

        // Perform DELETE request and verify response
        mockMvc.perform(delete("/entities/class/delete/1"))
                .andExpect(status().isNoContent()); // Assert HTTP 204 status

        // Verify service method was called once
        verify(projectClassService, times(1)).deleteEntity(1L);
    }

    /**
     * Tests retrieval of a single entity through the GET /entities/class/{id} endpoint.
     * Verifies the returned status code, JSON response structure, and service method invocation.
     */
    @Test
    void testGetProjectClass() throws Exception {
        // Mock service layer response
        ProjectClassDTO responseDTO = new ProjectClassDTO();
        responseDTO.setId(1L);

        when(projectClassService.getEntity(1L)).thenReturn(responseDTO);

        // Perform GET request and verify response
        mockMvc.perform(get("/entities/class/1"))
                .andExpect(status().isOk()) // Assert HTTP 200 status
                .andExpect(jsonPath("$.id").value(1L)); // Assert returned JSON contains id = 1

        // Verify service method was called once
        verify(projectClassService, times(1)).getEntity(1L);
    }

    /**
     * Tests retrieval of all entities through the GET /entities/class/getAll endpoint.
     * Verifies the returned status code, JSON response structure, and service method invocation.
     */
    @Test
    void testGetAllEntities() throws Exception {
        // Mock service layer response with a list of DTOs
        ProjectClassDTO entity1 = new ProjectClassDTO();
        entity1.setId(1L);

        ProjectClassDTO entity2 = new ProjectClassDTO();
        entity2.setId(2L);

        List<ProjectClassDTO> entities = Arrays.asList(entity1, entity2);

        when(projectClassService.getAll()).thenReturn(entities);

        // Perform GET request and verify response
        mockMvc.perform(get("/entities/class/getAll"))
                .andExpect(status().isOk()) // Assert HTTP 200 status
                .andExpect(jsonPath("$.length()").value(2)) // Assert returned JSON contains 2 items
                .andExpect(jsonPath("$[0].id").value(1L)) // Assert first item's id = 1
                .andExpect(jsonPath("$[1].id").value(2L)); // Assert second item's id = 2

        // Verify service method was called once
        verify(projectClassService, times(1)).getAll();
    }
}
