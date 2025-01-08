package com.example.demo.dao;

import com.example.demo.entities.ProjectClass;

public interface IProjectClassDAO {
    public ProjectClass save(ProjectClass projectClass);

    public ProjectClass findById(Long id);

    public void delete(Long id);
}
