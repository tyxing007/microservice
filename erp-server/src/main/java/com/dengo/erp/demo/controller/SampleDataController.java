package com.dengo.erp.demo.controller;

import com.dengo.erp.demo.service.SampleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Sample Data populate REST Controller
 * Controller that populate test DB by client request
 *
 * @author Sergey Petrovsky
 */
@RestController
public class SampleDataController {

    @Autowired SampleDataService sampleDateService;

    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void sample() throws IOException, NoSuchAlgorithmException {
        sampleDateService.populateSampleDB();
    }
}
