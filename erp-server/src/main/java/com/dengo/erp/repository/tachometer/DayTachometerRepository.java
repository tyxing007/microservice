package com.dengo.erp.repository.tachometer;

import com.dengo.erp.model.tachometer.DayTachometer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Day tachometer Repository
 *
 * @author Andrii Blyznuk
 */
@Repository
public interface DayTachometerRepository extends JpaRepository<DayTachometer, Long> {

}
