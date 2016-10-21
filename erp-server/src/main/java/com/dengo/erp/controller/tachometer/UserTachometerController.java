package com.dengo.erp.controller.tachometer;

import com.dengo.erp.model.tachometer.UserTachometer;
import com.dengo.erp.service.tachometer.UserTachometerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * User Tachometer Controller
 * Controller includes controllers that interface with tachometer service
 *
 * @author Andrii Blyznuk
 */

@RestController
public class UserTachometerController {

    @Autowired
    UserTachometerService userTachometerService;

    @RequestMapping(value = "/api/user/tachometer/user/{userId}", method = RequestMethod.GET)
    public UserTachometer getTachometer(@PathVariable("userId") Long userId){
        return userTachometerService.getTachometer(userId);
    }

}
