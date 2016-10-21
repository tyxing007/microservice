package com.dengo.erp.controller;

import com.dengo.erp.model.task.Priority;
import com.dengo.erp.model.task.Status;
import com.dengo.erp.model.task.Task;
import com.dengo.erp.service.task.LabelService;
import com.dengo.erp.service.task.PriorityService;
import com.dengo.erp.service.task.StatusService;
import com.dengo.erp.service.task.TaskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Controller handle obtaining tasks and creating them. Created at 22.07.16
 *
 * @author Sem Babenko
 */
@RestController
@RequestMapping(value = "/api/task")
public class TaskController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private PriorityService priorityService;

    @RequestMapping(method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Task> getTasks(HttpServletRequest httpServletRequest){
        LOGGER.debug("Processing request on URL:" + httpServletRequest.getRequestURI());
        return taskService.getTasks();
    }

    @RequestMapping(method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Task saveTask(HttpServletRequest httpServletRequest, @RequestBody Task task){
        LOGGER.debug("Processing request on URL:" + httpServletRequest.getRequestURI());
        try {
            System.out.println(new ObjectMapper().writeValueAsString(task));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return taskService.saveTask(task);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Task updateTask(HttpServletRequest httpServletRequest, @RequestBody Task task){
        LOGGER.debug("Processing request on URL:" + httpServletRequest.getRequestURI());
        return taskService.saveTask(task);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Task getTaskById(HttpServletRequest httpServletRequest, @PathVariable(value = "id") Long id){
        LOGGER.debug("Processing request on URL:" + httpServletRequest.getRequestURI());
        return taskService.findTaskById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteTask(HttpServletRequest httpServletRequest, @PathVariable(value = "id") Long id){
        LOGGER.debug("Processing request on URL:" + httpServletRequest.getRequestURI());
        taskService.deleteTaskById(id);
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Status> getStatus(HttpServletRequest httpServletRequest){
        LOGGER.debug("Processing request on URL:" + httpServletRequest.getRequestURI());
        return statusService.getStatus();
    }

    @RequestMapping(value = "/status/{status}", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Status saveStatus(HttpServletRequest httpServletRequest, @PathVariable(value = "status") String status){
        LOGGER.debug("Processing request on URL:" + httpServletRequest.getRequestURI());
        return statusService.saveStatus(status);
    }

    @RequestMapping(value = "/label", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<String> getLabel(HttpServletRequest httpServletRequest){
        LOGGER.debug("Processing request on URL:" + httpServletRequest.getRequestURI());
        return labelService.getLabels();
    }

    @RequestMapping(value = "/label/{label}", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public String saveLabel(HttpServletRequest httpServletRequest, @PathVariable(value = "label") String label){
        LOGGER.debug("Processing request on URL:" + httpServletRequest.getRequestURI());
        return labelService.saveLabel(label);
    }

    @RequestMapping(value = "/priority", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Priority> getPriority(HttpServletRequest httpServletRequest){
        LOGGER.debug("Processing request on URL:" + httpServletRequest.getRequestURI());
        return priorityService.getPriorities();
    }

    @RequestMapping(value = "/priority/{priority}", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Priority savePriority(HttpServletRequest httpServletRequest, @PathVariable(value = "priority") String priority){
        LOGGER.debug("Processing request on URL:" + httpServletRequest.getRequestURI());
        return priorityService.savePriority(priority);
    }
}