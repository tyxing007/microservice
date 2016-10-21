package com.tachometer.controller;

import com.tachometer.client.AuthServiceResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestController {

    @Autowired
    AuthServiceResource authServiceResource;

    @RequestMapping(value = "/api/test/{nameTest}", method = RequestMethod.GET)
    public String test(@PathVariable("nameTest") String nameTest){
        return nameTest + ", I'm from tachometer micro service";
    }

    @PreAuthorize("#oauth2.hasScope('ui')")
    @RequestMapping(value = "/api/principal", method = RequestMethod.GET)
    public Principal test(Principal principal){
        return  principal;
    }

    @RequestMapping(value = "/api/test")
    public String test1(){
        return authServiceResource.test();
    }

//    @PreAuthorize("#oauth2.hasScope('service')")
    @RequestMapping(value = "/api/principalT", method = RequestMethod.GET)
    public String testService(){
        System.out.println("oKi");
        return  "Oki";
    }

}
