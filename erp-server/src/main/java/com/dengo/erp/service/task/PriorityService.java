package com.dengo.erp.service.task;

import com.dengo.erp.model.task.Priority;
import com.dengo.erp.repository.task.PriorityRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Priority service process controller requests. Created at 22.07.16
 *
 * @author Sem Babenko
 */
@Service
public class PriorityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PriorityService.class);

    @Autowired
    private PriorityRepository priorityRepository;

    public List<Priority> getPriorities(){
        LOGGER.debug("Getting all Priorities");
        return priorityRepository.findAll();
    }

    public Priority savePriority(String priority){
        LOGGER.debug("Saving Priority with level: ", priority);
        return priorityRepository.save(new Priority(priority));
    }
}
