package com.dengo.erp.service.task;


import com.dengo.erp.model.task.Status;
import com.dengo.erp.repository.task.StatusRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Status service process controller requests which depend on status manipulation. Created at 22.07.16
 * @author Sem Babenko
 */
@Service
public class StatusService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusService.class);

    @Autowired
    private StatusRepository statusRepository;

    public List<Status> getStatus(){
        LOGGER.debug("Getting all Statuses");
        return statusRepository.findAll();
    }

    public Status saveStatus(String status){
        LOGGER.debug("Saving Status with name: ", status);
        return statusRepository.save(new Status(status));
    }
}
