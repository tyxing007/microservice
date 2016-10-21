package com.dengo.erp.repository;

import com.dengo.erp.model.Project;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Current repository process service request to database. Created at 01.08.16
 *
 * @author Sem Babenko
 */
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
