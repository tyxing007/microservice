package com.dengo.erp.controller;

import com.dengo.erp.model.Skill;
import com.dengo.erp.service.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Skill Controller
 * Controller includes controllers that interface with skill service
 *
 * @author Andrii Blyznuk
 */
@RestController
public class SkillController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SkillController.class);

    @Autowired SkillService skillService;

    //Method that returns a list of all skills
    @RequestMapping(value = "/api/skills", method = RequestMethod.GET)
    public List<Skill> getSkills(HttpServletRequest httpServletRequest){
        LOGGER.debug("Processing request on URL:" + httpServletRequest.getRequestURI());
        return skillService.findAll();
    }

}
