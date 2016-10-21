package com.dengo.erp.repository.task;

import com.dengo.erp.model.task.Priority;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Priority repository process handle Created at 22.07.16
 *
 * @author Sem Babenko
 */
public interface PriorityRepository extends JpaRepository<Priority, Long> {
}
