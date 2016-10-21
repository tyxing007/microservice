//package com.dengo.erp.controller;
//
//import com.dengo.erp.DengoErpApplication;
//import com.dengo.erp.model.Department;
//import com.dengo.erp.model.Skill;
//import com.dengo.erp.model.User;
//import com.dengo.erp.service.ApplicationService;
//import com.dengo.erp.service.DepartmentService;
//import com.dengo.erp.service.UserService;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
///**
// * Tests for Autocomplete Controller
// *
// * @author Taras Polishchuk
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = DengoErpApplication.class)
//@WebAppConfiguration
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//public class ApplicationControllerTest {
//
//    private MockMvc mvc;
//
//    @Autowired
//    private WebApplicationContext wac;
//
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    DepartmentService departmentService;
//
//    @Autowired
//    ApplicationService applicationService;
//
//    @Before
//    public void populate() {
//        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//
//        Skill drive = new Skill("drive");
//        Skill fly = new Skill("fly");
//
//        Department department = new Department("commodity");
//        departmentService.saveDepartment(department);
//
//        User user = new User("1@gmail.com").withSkills(drive, fly).withDepartment(department);
//        userService.saveUser(user);
//    }
//
//    @After
//    public void erase() {
//        userService.deleteAll();
//    }
//
//    @Test
//    public void autocompleteSkillTest() throws Exception {
//        mvc
//                .perform(get("/api/autocomplete/skillsName"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)));
//
//        assertTrue(applicationService.getAutocompleteData("skillsName").contains("fly"));
//        assertEquals(applicationService.getAutocompleteData("skillsName").get(0), "drive");
//    }
//
//    @Test
//    public void autocompleteDepartmentTest() throws Exception {
//        mvc
//                .perform(get("/api/autocomplete/departmentsName"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)));
//
//        assertEquals(applicationService.getAutocompleteData("departmentsName").get(0), "commodity");
//        assertEquals(departmentService.getDepartment(1L).getName(), applicationService.getAutocompleteData("departmentsName").get(0));
//    }
//}
