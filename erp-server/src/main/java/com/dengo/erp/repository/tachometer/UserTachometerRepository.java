package com.dengo.erp.repository.tachometer;

import com.dengo.erp.model.tachometer.DayTachometer;
import com.dengo.erp.model.tachometer.UserTachometer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * User tachometer Repository
 *
 * @author Andrii Blyznuk
 */
@Repository
public interface UserTachometerRepository extends JpaRepository<UserTachometer, Long>{

    @Query(value = "SELECT d FROM UserTachometer t inner join t.dayTachometers d where t.userId = ?1 and d.date = ?2")
    DayTachometer getDayTachometer(Long userId, LocalDate date);

}
