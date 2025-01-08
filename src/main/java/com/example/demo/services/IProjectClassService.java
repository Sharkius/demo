package com.example.demo.services;

import com.example.demo.dto.ProjectClassDTO;

public interface IProjectClassService {
    public ProjectClassDTO createEntity(ProjectClassDTO projectClassDTO);

    public void deleteEntity(Long id);
}
