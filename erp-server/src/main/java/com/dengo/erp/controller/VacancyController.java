package com.dengo.erp.controller;

import com.dengo.erp.model.Vacancy;
import com.dengo.erp.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Vacancy Controller
 * Controller includes controllers that interface with vacancy service
 *
 * @author Andrii Blyznuk
 */
@RestController
public class VacancyController {

    @Autowired
    VacancyService vacancyService;

    //The method that returns a list of vacancies
    @RequestMapping(value = "/api/vacancies",method = RequestMethod.GET)
    public List<Vacancy> getVacancies() {
        return vacancyService.getAll();
    }

    //A method that takes Object (Vacancy) to save the database.
    //In the present method, a condition where it is stored
    // vacancy to be its date of creation, if given jobs
    // in no time recorded lasted date
    @RequestMapping(value = "/api/vacancy/create",method = RequestMethod.POST)
    public Vacancy saveVacancy(@RequestBody Vacancy vacancy){
        return vacancyService.save(vacancy);
    }

    //A method that takes a unique ID to search for
    // the object in a dynyh and returns it to the client
    @RequestMapping(value = "/api/vacancies/{idVacancy}",method = RequestMethod.GET)
    public Vacancy getVacancy(@PathVariable("idVacancy") Long idVacancy){
        return vacancyService.getOne(idVacancy);
    }

}
