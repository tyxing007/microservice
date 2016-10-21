package com.dengo.erp.controller;

import com.dengo.erp.model.Project;
import com.dengo.erp.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 * Project controller process requests for project entity via project service. Created at 01.08.16
 *
 * @author Sem Babenko
 */

@RestController
@RequestMapping(value = "/api/project")
public class ProjectController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;

    @RequestMapping(method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public List<Project> getProjects(HttpServletRequest httpServletRequest){
        LOGGER.debug("Processing request on URL:" + httpServletRequest.getRequestURI());
        return projectService.getProjects();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public Project getProjectById(HttpServletRequest httpServletRequest, @PathVariable Long id){
        LOGGER.debug("Processing request on URL:" + httpServletRequest.getRequestURI());
        return projectService.getProjectById(id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public Project saveProject(HttpServletRequest httpServletRequest, @RequestBody Project project){
        LOGGER.debug("Processing request on URL:" + httpServletRequest.getRequestURI());
        return projectService.saveProject(project);
    }
}
