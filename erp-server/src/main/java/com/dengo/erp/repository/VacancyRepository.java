package com.dengo.erp.repository;

import com.dengo.erp.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * VacancyRepository
 * Repository that holds all vacancy requests to the database
 *
 * @author Andrii Blyznuk
 */
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

}
