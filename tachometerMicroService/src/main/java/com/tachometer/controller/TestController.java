package com.tachometer.controller;

import com.tachometer.client.AuthServiceResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestController {

    @Autowired
    AuthServiceResource authServiceResource;

    @PreAuthorize("#oauth2.hasScope('ui')")
    @RequestMapping(value = "/api/principal", method = RequestMethod.GET)
    public Principal test(Principal principal){
        return  principal;
    }


}
