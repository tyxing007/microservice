package com.dengo.erp.config;

import com.dengo.erp.DengoErpApplication;
import com.dengo.erp.demo.service.SampleDataService;
import com.dengo.erp.model.Department;
import com.dengo.erp.service.DepartmentService;
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

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for Department custom deserializer
 *
 * @author Sergey Petrovsky
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DengoErpApplication.class)
@WebAppConfiguration
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class JacksonDepartmentDeserializerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private SampleDataService sampleDataService;

    @Autowired
    private DepartmentService departmentService;


    @Before
    public void init() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void mockDepartmentTest() throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get("src/test/resources/mockDepartment.json"));
        sampleDataService.populateSampleDB();
        mvc
            .perform(post("/api/department/create")
                    .content(bytes).contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isCreated());
        assertEquals(new Department("Engineer Department"), departmentService.getDepartment(3L));
    }
}
