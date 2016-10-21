package com.dengo.erp.repository;

import com.dengo.erp.model.Step;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Step for Vacancy Repository
 * Repository is used only for class SampleDataService. This temporary repository
 *
 * @author Andrii Blyznuk
 */

public interface StepRepository extends JpaRepository<Step, Long> {
}
