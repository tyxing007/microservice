package com.petrovsky.ssta.controller;

import com.petrovsky.ssta.client.TachometerClientResource;
import com.petrovsky.ssta.client.UserClientResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @Autowired
    TachometerClientResource tachometerClientResource;

    @Autowired
    UserClientResource userClientResource;

    @RequestMapping(value = "/api/principal", method = RequestMethod.GET)
    public Principal getPrincipal (Principal principal){
        return principal;
    }

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        return principal;
    }


}
