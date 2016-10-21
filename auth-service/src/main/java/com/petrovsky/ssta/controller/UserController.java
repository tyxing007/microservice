package com.petrovsky.ssta.controller;

import com.petrovsky.ssta.client.TachometerClientResource;
import com.petrovsky.ssta.client.UserClientResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
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

    @Autowired
    private UserDetailsService userDetailsService;


    @RequestMapping(value = "/api/principal", method = RequestMethod.GET)
    public Principal getPrincipal (Principal principal){
        return principal;
    }

    @RequestMapping(value = "/api/principalT", method = RequestMethod.GET)
    public String getPrincipalFromTachometer (){
        return tachometerClientResource.getPrincipalFromTachometer();
    }

    @RequestMapping(value = "/api/test", method = RequestMethod.GET)
    public String test() {
        return "Oki";
    }

    @RequestMapping(value = "/api/tach/test", method = RequestMethod.GET)
    public Object test2() {
        return userClientResource.findByName("Marta");
    }

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        return principal;
    }


}
