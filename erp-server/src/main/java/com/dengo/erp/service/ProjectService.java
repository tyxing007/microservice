package com.dengo.erp.service;

import com.dengo.erp.model.Project;
import com.dengo.erp.model.task.Task;
import com.dengo.erp.repository.ProjectRepository;
import com.dengo.erp.repository.task.TaskRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Current service process controller request to project repository. Created at 01.08.16
 *
 * @author Sem Babenko
 */
@Service
public class ProjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectService.class);

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    public Project saveProject(Project project){
        LOGGER.debug("Save Project with name:", project.getName());
        return projectRepository.save(project);
    }

    public List<Project> getProjects(){
        LOGGER.debug("Get all Projects");
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id){
        LOGGER.debug("Get Project by ID:", id);
        Project project = projectRepository.findOne(id);
        Set<Task> tasks = taskRepository.findTasksByProjectId(project.getId());
        project.setTasks(tasks.stream().map(task -> {
            task.setProject(null);
            return task;
        }).collect(Collectors.toSet()));
        return project;
    }

    public void deleteProjectById(Long id){
        LOGGER.debug("Delete Project by ID:", id);
        projectRepository.delete(id);
    }

    public void deleteAll(){
        LOGGER.debug("Delete all Projects");
        projectRepository.deleteAll();
    }

}
