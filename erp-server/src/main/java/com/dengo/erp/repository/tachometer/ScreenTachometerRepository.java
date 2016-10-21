package com.dengo.erp.repository.tachometer;

import com.dengo.erp.model.tachometer.ScreenTachometer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Screen tachometer Repository
 *
 * @author Andrii Blyznuk
 */
@Repository
public interface ScreenTachometerRepository extends JpaRepository<ScreenTachometer, Long>{
}
