package com.example.demo;

import com.example.demo.controllers.ProjectClassController;
import com.example.demo.dto.ProjectClassDTO;
import com.example.demo.services.impl.ProjectClassService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProjectClassControllerIntegrationTest {

    private MockMvc mockMvc;

    @Mock
    private ProjectClassService projectClassService;

    @InjectMocks
    private ProjectClassController projectClassController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        // Initialize the MockMvc instance and ObjectMapper
        mockMvc = MockMvcBuilders.standaloneSetup(projectClassController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateEntity() throws Exception {
        ProjectClassDTO projectClassDTO = new ProjectClassDTO();
        projectClassDTO.id = 1L; // Set some test data

        // Mock the service's createEntity method
        when(projectClassService.createEntity(any(ProjectClassDTO.class))).thenReturn(projectClassDTO);

        // Perform the POST request to the controller and check the response
        mockMvc.perform(post("/entities/class/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(projectClassDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verify(projectClassService, times(1)).createEntity(any(ProjectClassDTO.class));
    }
}
