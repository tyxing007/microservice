package com.dengo.erp.service;

import com.dengo.erp.model.Vacancy;
import com.dengo.erp.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Vacancy Service
 * Service Vacancy Repository that binds to and includes methods of obtaining vacancies
 *
 * @author Andrii Blyznuk
 */
@Service
public class VacancyService {

    @Autowired
    VacancyRepository vacancyRepository;

    public Vacancy save(Vacancy vacancy){
        return vacancyRepository.save(vacancy);
    }

    public List<Vacancy> getAll(){
        return vacancyRepository.findAll();
    }

    public Vacancy getOne(Long idVacancy) {
        return vacancyRepository.findOne(idVacancy);
    }


}
