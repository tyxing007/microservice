package com.dengo.erp.repository;

import com.dengo.erp.model.Department;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Department Repository
 * Repository that holds all department requests to the database
 *
 * @author Sergey Petrovsky, Dmitry Sheremet, Andrii Blyznuk
 */
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Override
    @EntityGraph(value = "department.employeesAndParentS", type = EntityGraph.EntityGraphType.LOAD)
    List<Department> findAll();

    @Cacheable("departmentsName")
    @Query(value = "SELECT DISTINCT name FROM department", nativeQuery = true)
    List<String> listOfDepartmentsName();

}
