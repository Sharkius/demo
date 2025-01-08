package com.example.demo.services.impl;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dao.impl.ProjectClassDAO;
import com.example.demo.dto.ProjectClassDTO;
import com.example.demo.entities.ProjectClass;
import com.example.demo.services.IProjectClassService;

public class ProjectClassService implements IProjectClassService {

    @Autowired
    private ProjectClassDAO projectClassDAO;

    @Override
    public ProjectClassDTO createEntity(ProjectClassDTO projectClassDTO) {
        ProjectClass projectClass = convertToEntity(projectClassDTO);
        projectClassDAO.save(projectClass);
        return projectClassDTO;
    }

    @Override
    public void deleteEntity(Long id) {
        existingEntity(id);

        projectClassDAO.delete(id);
    }

    public ProjectClassDTO getEntity(Long id) {
        ProjectClass p = existingEntity(id);
        return convertToDTO(p);

    }

    public List<ProjectClassDTO> getAll() {
        List<ProjectClass> projectClasses = projectClassDAO.findAll();
        List<ProjectClassDTO> projectClassesDTO = new ArrayList<ProjectClassDTO>();
        for (ProjectClass projectClass : projectClasses) {
            projectClassesDTO.add(convertToDTO(projectClass));
        }
        return projectClassesDTO;
    }

    private ProjectClass existingEntity(Long id) {
        ProjectClass p = projectClassDAO.findById(id);
        if (p == null) {
            throw new InvalidParameterException("Id pas trouvé");
        }
        return p;
    }

    private ProjectClassDTO convertToDTO(ProjectClass projectClass) {
        ProjectClassDTO pDTO = new ProjectClassDTO();
        pDTO.id = projectClass.getId();
        return pDTO;
    }

    private ProjectClass convertToEntity(ProjectClassDTO projectClassDTO) {
        ProjectClass pc = new ProjectClass();
        pc.setId(projectClassDTO.id);
        return pc;
    }

}
