package com.dengo.erp.service.task;

import com.dengo.erp.model.task.Task;
import com.dengo.erp.repository.task.TaskRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Service process task controller requests. Created at 22.07.16
 * @author Sem Babenko
 */
@Service
public class TaskService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getTasks(){
        LOGGER.debug("Getting all Tasks");
        return taskRepository.findAll();
    }

    public Task saveTask(Task task){
        LOGGER.debug("Saving Task with title: ", task.getTitle());
        return taskRepository.save(task);
    }

    public Task findTaskById(Long id){
        LOGGER.debug("Getting Task with ID:", id);
        return taskRepository.findOne(id);
    }

    public void deleteTasks(){
        LOGGER.debug("Deleting all Tasks");
        taskRepository.deleteAll();
    }

    public void deleteTaskById(Long id){
        LOGGER.debug("Deleting Task with ID:", id);
        taskRepository.delete(id);
    }

    public Set<Task> findTasksByProjectId(Long id){
        LOGGER.debug("Getting Tasks by Project ID:", id);
        return taskRepository.findTasksByProjectId(id);
    }
}
