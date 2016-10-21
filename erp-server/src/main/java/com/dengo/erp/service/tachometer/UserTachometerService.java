package com.dengo.erp.service.tachometer;

import com.dengo.erp.client.TachometerClient;
import com.dengo.erp.model.tachometer.UserTachometer;
import com.dengo.erp.repository.tachometer.UserTachometerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User Tachometer Service
 *
 * @author Andrii Blyznuk
 */

@Service
public class UserTachometerService {

    @Autowired
    UserTachometerRepository userTachometerRepository;

    @Autowired
    TachometerClient tachometerClient;

    public UserTachometer getTachometer (Long userId){
        return userTachometerRepository.findOne(userId);
    }

    public String test(String testName) {
        return tachometerClient.test(testName);
    }
}
