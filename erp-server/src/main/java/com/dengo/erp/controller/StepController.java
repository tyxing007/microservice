package com.dengo.erp.controller;

import com.dengo.erp.model.Step;
import com.dengo.erp.service.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Step Controller
 * Controller includes controllers that interface with step service
 *
 * @author Andrii Blyznuk
 */

@RestController
public class StepController {

    @Autowired
    StepService stepService;

    @RequestMapping(value = "/api/step", method = RequestMethod.POST)
    public Step saveStep (@RequestBody Step step){
        return stepService.save(step);
    }

}
