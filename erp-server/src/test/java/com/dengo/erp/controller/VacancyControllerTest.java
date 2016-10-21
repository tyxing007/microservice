//package com.dengo.erp.controller;
//
//import com.dengo.erp.DengoErpApplication;
//import com.dengo.erp.model.User;
//import com.dengo.erp.model.Vacancy;
//import com.dengo.erp.model.enums.TypeVacancy;
//import com.dengo.erp.service.UserService;
//import com.dengo.erp.service.VacancyService;
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
//import static org.hamcrest.Matchers.is;
//import static org.junit.Assert.assertTrue;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
///**
// * Tests for Preview Photo Controller
// *
// * @author Taras Polishchuk
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = DengoErpApplication.class)
//@WebAppConfiguration
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//public class VacancyControllerTest {
//
//    private MockMvc mvc;
//
//    @Autowired
//    private WebApplicationContext wac;
//
//    @Autowired
//    VacancyService vacancyService;
//
//    @Autowired
//    private UserService userService;
//
//    @Before
//    public void populate() {
//        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//        User candidate = new User("Candidate");
//        Vacancy php = new Vacancy("PHP", TypeVacancy.OPENED);
//        php.addCandidate(userService.saveUser(candidate));
//        vacancyService.save(php);
//    }
//
//    @Test
//    public void getVacanciesTest() throws Exception {
//        mvc
//                .perform(get("/api/vacancies"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)));
//
//        assertTrue(vacancyService.getOne(1L).getCandidates().contains(new User("Candidate")));
//    }
//
//    @Test
//    public void getSingleVacancyTest() throws Exception {
//        mvc
//                .perform(get("/api/vacancies/{vacancyId}", 1L))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.name", is("PHP")));
//    }
//}
