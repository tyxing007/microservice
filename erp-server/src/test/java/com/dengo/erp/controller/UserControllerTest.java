//package com.dengo.erp.controller;
//
//import com.dengo.erp.DengoErpApplication;
//import com.dengo.erp.model.User;
//import com.dengo.erp.model.enums.TypeUser;
//import com.dengo.erp.service.UserService;
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
//import static org.hamcrest.Matchers.hasSize;
//import static org.hamcrest.Matchers.is;
//import static org.junit.Assert.assertEquals;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
///**
// * Tests for Department Controller
// *
// * @author Sergey Petrovsky
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = DengoErpApplication.class)
//@WebAppConfiguration
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//public class UserControllerTest {
//
//    private MockMvc mvc;
//
//    @Autowired
//    private WebApplicationContext wac;
//
//    @Autowired
//    UserService userService;
//
//    @Before
//    public void populate() {
//        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//
//        User user1 = new User("1@com.com");
//        User user2 = new User("2@com.com");
//        User user3 = new User("3@com.com");
//        user1.setType(TypeUser.CUSTOMER);
//
//        userService.saveUser(user1);
//        userService.saveUser(user2);
//        userService.saveUser(user3);
//    }
//
//    @After
//    public void erase() {
//        userService.deleteAll();
//    }
//
//    @Test
//    public void getUsersTest() throws Exception {
//        mvc
//                .perform(get("/api/users"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(3)));
//    }
//
//    @Test
//    public void getUserTest() throws Exception {
//        mvc
//                .perform(get("/api/user/{UserID}", 1L))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.email", is("1@com.com")));
//    }
//
//    @Test
//    public void saveUserTest() throws Exception {
//        mvc
//                .perform(post("/api/user/create").content("{\"email\":\"4@com.com\"}").contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(status().isCreated());
//
//        assertEquals(new User("4@com.com"), userService.getUser(4L));
//    }
//
//    @Test
//    public void updateUserTest() throws Exception {
//        mvc
//                .perform(post("/api/user/create").content("{\"email\":\"5@com.com\"}").contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(status().isCreated());
//
//        assertEquals(new User("5@com.com"), userService.getUser(4L));
//    }
//
//    @Test
//    public void createUserTest() throws Exception {
//        mvc
//                .perform(post("/api/user/create").content("{\"email\":\"test@com.com\"}").contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(status().isCreated());
//
//        assertEquals(new User("test@com.com"), userService.getUser(4L));
//    }
//}
