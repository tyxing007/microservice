package com.dengo.erp.repository;

import com.dengo.erp.model.Skill;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * SkillRepository
 * Repository that holds all skill requests to the database
 *
 * @author Andrii Blyznuk
 */
public interface SkillRepository extends JpaRepository<Skill, String> {


    @Cacheable("skillsName")
    @Query(value = "SELECT DISTINCT name FROM skill", nativeQuery = true)
    List<String> listOfSkillsName();

}
