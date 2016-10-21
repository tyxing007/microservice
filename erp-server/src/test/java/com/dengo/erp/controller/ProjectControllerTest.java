package com.dengo.erp.controller;

import com.dengo.erp.DengoErpApplication;
import com.dengo.erp.model.Project;
import com.dengo.erp.model.task.Task;
import com.dengo.erp.service.ProjectService;
import com.dengo.erp.service.task.TaskService;
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

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for Project Controller
 *
 * @author Taras Polishchuk
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DengoErpApplication.class)
@WebAppConfiguration
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProjectControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    ProjectService projectService;

    @Autowired
    TaskService taskService;

    @Before
    public void populate() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

        Project project = new Project();
        project.setName("CRM");

        Task task1 = new Task();
        task1.setTitle("Task-1");
        task1.setType("task");

        projectService.saveProject(project);

        task1.setProject(project);

        taskService.saveTask(task1);
    }

    @After
    public void erase() {
        taskService.deleteTasks();
        projectService.deleteAll();
    }

    @Test
    public void getAllProjectsTest() throws Exception {
        mvc
                .perform(get("/api/project"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        assertEquals("CRM", projectService.getProjectById(1L).getName());
    }

    @Test
    public void getProjectsByIdTest() throws Exception {
        mvc
                .perform(get("/api/project/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("CRM")));

        assertEquals(projectService.getProjectById(1L).getName(), taskService.findTaskById(1L).getProject().getName());
    }

    @Test
    public void createProjectsTest() throws Exception {
        mvc
                .perform(post("/api/project").content("{\"name\": \"Interceptor\", \"area\": \"square\"}").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is("Interceptor")));

        assertEquals(projectService.getProjectById(2L).getArea(), "square");
    }

}
