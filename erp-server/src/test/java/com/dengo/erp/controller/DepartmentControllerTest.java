package com.dengo.erp.controller;

import com.dengo.erp.DengoErpApplication;
import com.dengo.erp.model.Department;
import com.dengo.erp.service.DepartmentService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for Department Controller
 *
 * @author Sergey Petrovsky
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DengoErpApplication.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DepartmentControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    DepartmentService departmentService;

    @Before
    public void populate() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

        List<Department> list = new ArrayList<>();
        Department company = new Department("Main Company", null);
        Department managementDpt = new Department("Management Department", 1L);

        list.add(company);
        list.add(managementDpt);

        list.forEach(item -> {
            departmentService.saveDepartment(item);
        });
    }

    @After
    public void erase() {
        departmentService.deleteAll();
    }

    @Test
    public void getAllDepartmentsTest() throws Exception {
        mvc
                .perform(get("/api/departments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void getByIdTest() throws Exception {
        mvc
                .perform(get("/api/department/{id}", 2L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.name", is("Management Department")));
    }

    @Test
    public void saveDepartmentTest() throws Exception {
        mvc
                .perform(post("/api/department/create").content("{\"name\":\"Sales Department\"}").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());

        assertEquals(new Department("Sales Department"), departmentService.getDepartment(3L));
    }

    @Test
    public void updateDepartmentTest() throws Exception {
        mvc
                .perform(post("/api/department/create").content("{\"name\":\"Engineering Department\"}").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());

        assertEquals(new Department("Engineering Department"), departmentService.getDepartment(3L));
    }
}
