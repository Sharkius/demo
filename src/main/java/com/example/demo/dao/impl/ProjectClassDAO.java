package com.example.demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dao.IProjectClassDAO;
import com.example.demo.entities.ProjectClass;
import com.example.demo.repositories.ProjectClassRepository;

public class ProjectClassDAO implements IProjectClassDAO {

    @Autowired
    private ProjectClassRepository projectClassRepository;

    @Override
    public ProjectClass save(ProjectClass projectClass) {
        return projectClassRepository.save(projectClass);
    }

    public void delete(Long id) {
        projectClassRepository.deleteById(id);
    }

    public ProjectClass findById(Long id) {
        return projectClassRepository.findById(id).orElse(null);
    }

    public List<ProjectClass> findAll() {
        return projectClassRepository.findAll();
    }

}
