package com.dengo.erp.service;

import com.dengo.erp.repository.DepartmentRepository;
import com.dengo.erp.repository.SkillRepository;
import com.dengo.erp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class ApplicationService is service, that handle ApplicationController.
 * Use @Autowired for connect to necessary repositories
 *
 * @author Andrii Blyznuk
 */
@Service
public class ApplicationService {

    @Autowired DepartmentRepository departmentRepository;
    @Autowired SkillRepository skillRepository;
    @Autowired UserRepository userRepository;

    //This method is executed when the user uses autocomplete.
    // The method accepts a keyword on which will use a
    // special method for obtaining objects from the database
    @Transactional(readOnly = true)
    public List<?> getAutocompleteData(String itemName) {
        switch (itemName) {
            case "departmentsName": return departmentRepository.listOfDepartmentsName();
            case "skillsName": return skillRepository.listOfSkillsName();
            case "responsible": return userRepository.listOfResponsible();
            default: throw new IllegalArgumentException();
        }
    }
}
