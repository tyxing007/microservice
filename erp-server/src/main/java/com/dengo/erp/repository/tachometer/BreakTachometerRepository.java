package com.dengo.erp.repository.tachometer;

import com.dengo.erp.model.tachometer.BreakTachometer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Break tachometer Repository
 *
 * @author Andrii Blyznuk
 */
@Repository
public interface BreakTachometerRepository extends JpaRepository<BreakTachometer, Long> {
}
