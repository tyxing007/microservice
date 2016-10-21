package com.dengo.erp.controller;

import com.dengo.erp.DengoErpApplication;
import com.dengo.erp.model.User;
import com.dengo.erp.service.PhotoService;
import com.dengo.erp.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for Preview Photo Controller
 *
 * @author Taras Polishchuk
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DengoErpApplication.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PhotoControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    PhotoService photoService;

    @Autowired
    UserService userService;

    @Before
    public void populate() throws IOException, NoSuchAlgorithmException {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

        User user1 = new User("1@com.com");
        userService.saveUser(user1);
    }

    @After
    public void erase() {
        userService.deleteAll();
    }

    @Test
    public void uploadImageUserTest() throws Exception {
        FileInputStream stream = new FileInputStream("src/test/resources/download.jpg");
        MockMultipartFile multipartFile = new MockMultipartFile("file", "download.jpg", MediaType.IMAGE_JPEG_VALUE, stream);

        mvc
                .perform(fileUpload("/api/image/upload/user/{userId}", 1L)
                        .file(multipartFile)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk());
    }

    @Test
    public void equalityImageUserTest() throws Exception {
        FileInputStream stream1 = new FileInputStream("src/test/resources/download.jpg");

        assertTrue("54f2e4c1dc3391f792f30494a9319cda".equals(PhotoService.getHash(stream1, "MD5", 1024)));
    }

    @Test(expected = AssertionError.class)
    public void notEqualityImageUserTest() throws Exception {
        FileInputStream stream1 = new FileInputStream("src/test/resources/download.jpg");

        assertTrue("54f2e4c1dc3f391f792f30494a9319cda".equals(PhotoService.getHash(stream1, "MD5", 1024)));
    }

    @Test
    public void equalityOfCreatedImageUserTest() throws Exception {
        File originalS = new File("./resources/images/original");
        File previewS = new File("./resources/images/preview");
        File[] originalSF = originalS.listFiles();
        File[] previewSF = previewS.listFiles();

        assertTrue(originalSF[0].getName().equals(previewSF[0].getName()));
    }

    @Test
    public void removeImageUserTest() throws Exception {
        FileInputStream stream = new FileInputStream("src/test/resources/download.jpg");
        MockMultipartFile multipartFile = new MockMultipartFile("file", "download.jpg", MediaType.IMAGE_JPEG_VALUE, stream);

        mvc
                .perform(fileUpload("/api/image/upload/user/{userId}", 1L)
                        .file(multipartFile)
                        .contentType(MediaType.MULTIPART_FORM_DATA));

        mvc
                .perform(post("/api/image/delete/user/{userId}", 1L))
                .andExpect(status().isNoContent());

        assertNull(userService.getUser(1L).getPhoto());
    }
}