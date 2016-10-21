package com.dengo.erp.controller;

import com.dengo.erp.model.Department;
import com.dengo.erp.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Department REST Controller
 * Controller that handles all department requests from front-end
 *
 * @author Sergey Petrovsky
 */
@RestController
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value = "api/departments", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Department> getDepartments (HttpServletRequest httpServletRequest) {
        LOGGER.debug("Processing request on URL:" + httpServletRequest.getRequestURI());
        return departmentService.getDepartments();
    }

    @RequestMapping(value = "api/department/{departmentId}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Department getDepartment (HttpServletRequest httpServletRequest, @PathVariable Long departmentId) {
        LOGGER.debug("Processing request on URL:" + httpServletRequest.getRequestURI());
        return departmentService.getDepartment(departmentId);
    }

    @RequestMapping(value = "api/department/create", method = RequestMethod.POST, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void saveDepartment (HttpServletRequest httpServletRequest, @RequestBody Department department) {
        LOGGER.debug("Processing request on URL:" + httpServletRequest.getRequestURI());
        departmentService.saveDepartment(department);
    }

    @RequestMapping(value = "api/department/delete/{departmentId}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteDepartment (HttpServletRequest httpServletRequest, @PathVariable("departmentId") Long departmentId) {
        LOGGER.debug("Processing request on URL:" + httpServletRequest.getRequestURI());
        departmentService.deleteDepartmentById(departmentId);
    }

    @RequestMapping(value = "/api/department/{departmentId}/bind/{userId}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void bindDepartmentWithUser(HttpServletRequest httpServletRequest, @PathVariable("departmentId") Long departmentId, @PathVariable("userId") Long userId) throws IOException {
        LOGGER.debug("Processing request on URL:" + httpServletRequest.getRequestURI());
        departmentService.bindDepartmentWithUser(departmentId, userId);
    }
}
