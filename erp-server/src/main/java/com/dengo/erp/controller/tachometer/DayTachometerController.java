package com.dengo.erp.controller.tachometer;

import com.dengo.erp.model.tachometer.DayTachometer;
import com.dengo.erp.service.tachometer.DayTachometerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * Day Tachometer Controller
 * Controller includes controllers that interface with tachometer service
 *
 * @author Andrii Blyznuk
 */

@RestController
public class DayTachometerController {

    @Autowired
    DayTachometerService dayTachometerService;

    @RequestMapping(value = "/api/day/tachometer/user/{userId}/day/{day}", method = RequestMethod.GET)
    public DayTachometer getDayTachometer(@PathVariable("userId") Long userId, @PathVariable("day") String date){
        return dayTachometerService.getDayTachometer(userId, LocalDate.parse(date));
    }

}
