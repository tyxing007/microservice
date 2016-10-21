package com.dengo.erp.service;

import com.dengo.erp.model.Skill;
import com.dengo.erp.repository.SkillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Skill Service
 * Service Skill Repository that binds to and includes methods of obtaining Skills
 *
 * @author Andrii Blyznuk
 */
@Service
public class SkillService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SkillService.class);

    @Autowired
    SkillRepository skillRepository;

    public List<Skill> findAll(){
        LOGGER.debug("Getting all Skills");
        return skillRepository.findAll();
    }

    public Skill findOne(String name){
        Skill skill = skillRepository.findOne(name);
        if (Optional.ofNullable(skill).isPresent()) {
            LOGGER.debug("Getting Skill with name:" + name);
            return skill;
        } else {
            LOGGER.debug("Skill with name:" + name + " doesn't exist");
            return null;
        }
    }

    public void deleteAll() {
        LOGGER.debug("Deleting all Skills");
        skillRepository.deleteAll();
    }
}
