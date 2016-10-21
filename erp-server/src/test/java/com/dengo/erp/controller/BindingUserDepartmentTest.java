//package com.dengo.erp.controller;
//
//import com.dengo.erp.DengoErpApplication;
//import com.dengo.erp.model.Department;
//import com.dengo.erp.model.User;
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
//import static org.hamcrest.Matchers.hasItems;
//import static org.junit.Assert.assertThat;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
///**
// * Tests for checking binding User and Department
// *
// * @author Sergey Petrovsky
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = DengoErpApplication.class)
//@WebAppConfiguration
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//public class BindingUserDepartmentTest {
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
//    @Before
//    public void populate() {
//        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//
//        Department department_1 = new Department("Department_1");
//        User user_1 = new User("User_1");
//
//        userService.saveUser(user_1);
//        departmentService.saveDepartment(department_1);
//    }
//
//    @After
//    public void erase() {
//        departmentService.deleteAll();
//        userService.deleteAll();
//    }
//
//    @Test
//    public void bindingUserWithDepartmentTest() throws Exception {
//        mvc
//                .perform(get("/api/user/{userId}/bind/{departmentID}", 1L, 1L))
//                .andExpect(status().isOk());
//
//        assertThat(userService.getUser(1L).getDepartments(), hasItems(departmentService.getDepartment(1L)));
//    }
//
//    @Test
//    public void bindingDepartmentWithUserTest() throws Exception {
//        mvc
//                .perform(get("/api/department/{departmentID}/bind/{userID}", 1L, 1L))
//                .andExpect(status().isOk());
//
//        assertThat(departmentService.getDepartment(1L).getEmployees(), hasItems(userService.getUser(1L)));
//    }
//}
