package com.dengo.erp.controller;

import com.dengo.erp.service.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Class ApplicationController is controller, that handle main requests.
 * Use @Autowired for connect to necessary services
 * Use RequestMapping for handle request from the client-side
 *
 * @author Andrii Blyznuk
 */
@RestController
public class ApplicationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SkillController.class);

    @Autowired ApplicationService applicationService;

    // For now only skills are accessible for autocomplete request
    //method for AutoComplete he called from the client and takes on a keyword that
    //transmits to the service which get specific objects on specific repository
    // objects and returns a List
    @RequestMapping(value = "/api/autocomplete/{itemName}", method = RequestMethod.GET)
    public List<?> autocompleteData(HttpServletRequest httpServletRequest, @PathVariable String itemName) {
        LOGGER.debug("Processing request on URL:" + httpServletRequest.getRequestURI());
        return applicationService.getAutocompleteData(itemName);
    }
}
