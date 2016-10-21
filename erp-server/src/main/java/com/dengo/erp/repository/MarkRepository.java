package com.dengo.erp.repository;

import com.dengo.erp.model.Mark;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Mark Repository
 * Repository that holds all mark requests to the database
 *
 * @author Andrii Blyznuk
 */

public interface MarkRepository extends JpaRepository<Mark,Long>{


}
