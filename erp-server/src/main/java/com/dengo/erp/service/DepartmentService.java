package com.dengo.erp.service;

import com.dengo.erp.model.Department;
import com.dengo.erp.model.User;
import com.dengo.erp.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Department Service
 * Service that handles all department requests from user controller
 *
 * @author Sergey Petrovsky
 */
@Service
public class DepartmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentService.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserService userService;

    public List<Department> getDepartments() {
        LOGGER.debug("Getting all Departments");
        return departmentRepository.findAll();
    }

    public void deleteAll() {
        LOGGER.debug("Deleting all Departments");
        departmentRepository.deleteAll();
    }

    public void deleteDepartmentById(Long id) {
        LOGGER.debug("Deleting Departments with ID:" + id);
        departmentRepository.delete(id);
    }

    public Department getDepartment(Long id) {
        Department department = departmentRepository.findOne(id);
        if (Optional.ofNullable(department).isPresent()) {
            LOGGER.debug("Getting Department with ID:" + id);
            return department;
        } else {
            LOGGER.debug("Department with ID:" + id + ", doesn't exist");
            return null;
        }
    }

    public Department saveDepartment(Department department) {
        LOGGER.debug("Saving Department with name: " + department.getName());
        return departmentRepository.save(department);
    }

    public Department bindDepartmentWithUser(Long departmentId, Long userId) {
        Department department = departmentRepository.findOne(departmentId);
        User user = userService.getUser(userId);
        if(Optional.ofNullable(department).isPresent() && Optional.ofNullable(user).isPresent()) {
            LOGGER.debug("Binding Department with ID:" + departmentId + " and User with ID:" + userId);
            department.withEmployee(user);
            return saveDepartment(department);
        } else {
            LOGGER.debug("Department with ID:" + departmentId + " and User with ID:" + userId + "don't exist");
            return null;
        }
    }
}
