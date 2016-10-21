//package com.dengo.erp.controller;
//
//import com.dengo.erp.DengoErpApplication;
//import com.dengo.erp.model.User;
//import com.dengo.erp.model.task.Label;
//import com.dengo.erp.model.task.Priority;
//import com.dengo.erp.model.task.Status;
//import com.dengo.erp.model.task.Task;
//import com.dengo.erp.repository.task.PriorityRepository;
//import com.dengo.erp.repository.task.StatusRepository;
//import com.dengo.erp.service.UserService;
//import com.dengo.erp.service.task.LabelService;
//import com.dengo.erp.service.task.TaskService;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.http.MediaType;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.List;
//
//import static org.hamcrest.Matchers.*;
//import static org.junit.Assert.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
///**
// * Tests for task controller. Created at 26.07.16
// *
// * @author Sem Babenko
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = DengoErpApplication.class)
//@WebAppConfiguration
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//public class TaskControllerTest {
//
//    private MockMvc mvc;
//
//    @Autowired
//    private WebApplicationContext wac;
//
//    @Autowired
//    private TaskService taskService;
//
//    @Autowired
//    private LabelService labelService;
//
//    @Autowired
//    private StatusRepository statusRepository;
//
//    @Autowired
//    private PriorityRepository priorityRepository;
//
//    @Autowired
//    private UserService userService;
//
//    @Before
//    public void populate() {
//        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//
//        Task task1 = new Task();
//        Task task2 = new Task();
//        Label label1 = new Label("high priority");
//        Label label2 = new Label("deadline in 2 weeks");
//        Status status = new Status("available");
//        Priority priority = new Priority("high");
//
//        priorityRepository.save(priority);
//        statusRepository.save(status);
//
//        task1.setTitle("Task-1");
//        task1.setType("task");
//        task1.setStatus(status);
//        task1.setPriority(priority);
//        task2.setTitle("Task-2");
//        task2.setType("test");
//        task2.setLabels(new HashSet<>(Arrays.asList(label1, label2)));
//
//        User first = new User("1@gmail.com");
//        User second = new User("2@gmail.com");
//
//        userService.saveUser(first);
//        userService.saveUser(second);
//
//        task1.setAuthor(first);
//        task2.setAuthor(second);
//
//        taskService.saveTask(task1);
//        taskService.saveTask(task2);
//    }
//
//    @After
//    public void erase() {
//        taskService.deleteTasks();
//    }
//
//    @Test
//    public void getAllTasksTest() throws Exception {
//        mvc
//                .perform(get("/api/task"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)));
//
//        assertEquals("Task-1", taskService.findTaskById(1L).getTitle());
//    }
//
//    @Test
//    public void getTaskByIdTest() throws Exception {
//        mvc
//                .perform(get("/api/task/{id}", 1L))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.title", is("Task-1")));
//        assertEquals("task", taskService.findTaskById(1L).getType());
//    }
//
//    @Test
//    public void createTaskTest() throws Exception {
//        mvc
//                .perform(post("/api/task")
//                        .content("{\"id\": \"3\", \"title\": \"Task-3\", \"type\": \"task\", \"author\": {\"id\":\"3\", \"email\": \"12@gmail.com\"}}")
//                        .contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id", notNullValue()))
//                .andExpect(jsonPath("$.title", is("Task-3")));
//    }
//
//    @Test
//    public void removeTaskTest() throws Exception {
//        List<Task> tasksBeforeDelete = taskService.getTasks();
//        assertNotNull(tasksBeforeDelete);
//        assertEquals(tasksBeforeDelete.size(), 2);
//
//        mvc
//                .perform(delete("/api/task/{id}", 1L))
//                .andExpect(status().isNoContent());
//
//        List<Task> tasksAfterDelete = taskService.getTasks();
//        assertNotNull(tasksAfterDelete);
//        assertEquals(tasksAfterDelete.size(), 1);
//    }
//
//    @Test
//    public void updateTaskTest() throws Exception {
////        User user = new User("12@gmail.com");
////
////        userService.saveUser(user);
//        mvc
//                .perform(put("/api/task")
//                        .content("{\"id\": \"2\", \"title\":\"Updated Task\", \"type\":\"task\", \"author\": {\"id\":\"3\", \"email\": \"12@gmail.com\"}}")
//                        .contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(2)))
//                .andExpect(jsonPath("$.title", is("Updated Task")));
//
//        Task updatedTask = taskService.findTaskById(2L);
//        assertNotNull(updatedTask);
//        assertEquals(updatedTask.getId().longValue(), 2L);
//        assertEquals(updatedTask.getTitle(), "Updated Task");
//    }
//
//    @Test
//    public void statusTaskTest() throws Exception {
//        mvc
//                .perform(get("/api/task/status"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)));
//
//        assertTrue(taskService.getTasks().get(0).getStatus().getName().equals("available"));
//    }
//
//    @Test
//    public void specifyStatusTaskTest() throws Exception {
//        mvc
//                .perform(post("/api/task/status/{status}", "available"))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.name", is("available")));
//    }
//
////    @Test
////    public void labelTaskTest() throws Exception {
////        mvc
////                .perform(get("/api/task/label"))
////                .andExpect(status().isOk())
////                .andExpect(jsonPath("$", hasSize(2)));
////
////        assertEquals("deadline", labelService.getLabels().get(0));
////    }
//
//    @Test
//    public void specifyLabelTaskTest() throws Exception {
//        mvc
//                .perform(post("/api/task/label/{label}", "label")
//                        .content("{\"id\":\"label\"}")
//                        .contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    public void priorityTaskTest() throws Exception {
//        mvc
//                .perform(get("/api/task/priority"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)));
//
//        assertTrue(taskService.getTasks().get(0).getPriority().getLevel().equals("high"));
//    }
//
//    @Test
//    public void specifyPriorityTaskTest() throws Exception {
//        mvc
//                .perform(post("/api/task/priority/{priority}", "high"))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id", is(2)));
//    }
//}
