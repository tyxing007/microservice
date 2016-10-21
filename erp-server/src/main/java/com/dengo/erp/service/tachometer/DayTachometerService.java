package com.dengo.erp.service.tachometer;

import com.dengo.erp.model.tachometer.DayTachometer;
import com.dengo.erp.repository.tachometer.DayTachometerRepository;
import com.dengo.erp.repository.tachometer.UserTachometerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Day Tachometer Service
 *
 * @author Andrii Blyznuk
 */
@Service
public class DayTachometerService {

    @Autowired
    DayTachometerRepository dayTachometerRepository;

    @Autowired
    UserTachometerRepository userTachometerRepository;

    public DayTachometer getDayTachometer(Long userId, LocalDate day){
        return userTachometerRepository.getDayTachometer(userId, day);
    }

}
