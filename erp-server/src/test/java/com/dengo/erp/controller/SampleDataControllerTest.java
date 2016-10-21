package com.dengo.erp.controller;

import com.dengo.erp.DengoErpApplication;
import com.dengo.erp.demo.service.SampleDataService;
import com.dengo.erp.model.Department;
import com.dengo.erp.model.User;
import com.dengo.erp.service.DepartmentService;
import com.dengo.erp.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for Sample Demo Controller
 *
 * @author Taras Polishchuk
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DengoErpApplication.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SampleDataControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    UserService userService;

    @Autowired
    SampleDataService sampleDataService;

    @Before
    public void populate() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void linkedEntitiesTest() throws Exception {
        mvc
                .perform(get("/demo"))
                .andExpect(status().isOk());

        Set<Department> departments2User = new HashSet<>();
        departments2User.add(departmentService.getDepartment(2L));
        Set<Department> departments1User = new HashSet<>();
        departments1User.add(departmentService.getDepartment(1L));

        Set<User> users = new HashSet<>();
        users.add(userService.getUser(2L));

        assertEquals(departmentService.getDepartment(1L).getDirector(), userService.getUser(2L));
        assertEquals(departmentService.getDepartment(2L).getDirector(), userService.getUser(1L));
        assertEquals(userService.getUser(1L).getDepartments(), departments1User);
        assertEquals(userService.getUser(2L).getDepartments(), departments2User);
        assertEquals(departmentService.getDepartment(2L).getEmployees(), users);

    }

    @Test
    public void creatingEntitiesTest() throws Exception {
        mvc
                .perform(get("/demo"))
                .andExpect(status().isOk());

        assertEquals(new Department("Sales Department"), departmentService.getDepartment(1L));
    }
}