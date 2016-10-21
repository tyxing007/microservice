package com.dengo.erp.service.task;

import com.dengo.erp.model.task.Label;
import com.dengo.erp.repository.task.LabelRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Label service process controller requests. Created at 22.07.16
 * @author Sem Babenko
 */
@Service
public class LabelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LabelService.class);

    @Autowired
    private LabelRepository labelRepository;

    public List<String> getLabels(){
        LOGGER.debug("Getting all Labels");
        return labelRepository.findAll().stream().map(Label::getLabel).collect(Collectors.toList());
    }

    public String saveLabel(String label){
        LOGGER.debug("Saving Label ");
        return labelRepository.save(new Label(label)).getLabel();
    }
}
