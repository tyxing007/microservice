package com.dengo.erp.controller;

import com.dengo.erp.DengoErpApplication;
import com.dengo.erp.model.Skill;
import com.dengo.erp.model.User;
import com.dengo.erp.service.SkillService;
import com.dengo.erp.service.UserService;
import org.junit.After;
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

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for Skill Controller
 *
 * @author Taras Polishchuk
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DengoErpApplication.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SkillControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    SkillService skillService;

    @Autowired
    UserService userService;

    @Before
    public void populate() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

        Skill fly = new Skill("fly");
        Skill drive = new Skill("drive");

        User user = new User("1@com.com").withSkills(fly, drive);
        userService.saveUser(user);
    }

    @After
    public void erase() {
        userService.deleteAll();
        skillService.deleteAll();
    }

    @Test
    public void setSkillTest() throws Exception {
        mvc
                .perform(get("/api/skills"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(status().isOk());

        assertEquals(new Skill("fly"), skillService.findOne("fly"));
        assertTrue(userService.getUser(1L).getSkills().containsAll(skillService.findAll()));

    }

    @Test
    public void setUsersToSkillTest() throws Exception {
        mvc
                .perform(get("/api/skills"))
                .andExpect(status().isOk());

        assertEquals(skillService.findOne("fly").getUsers().get(0).getEmail(), "1@com.com");
        assertTrue(skillService.findOne("fly").getUsers().contains(userService.getUser(1L)));
    }
}
