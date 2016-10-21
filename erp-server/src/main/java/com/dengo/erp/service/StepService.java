package com.dengo.erp.service;


import com.dengo.erp.model.Step;
import com.dengo.erp.repository.StepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Step Service
 * Service Step Repository that binds to and includes methods of obtaining steps
 *
 * @author Andrii Blyznuk
 */

@Service
public class StepService {

    @Autowired
    StepRepository stepRepository;

    public Step save(Step step){
        return stepRepository.save(step);
    }


}
