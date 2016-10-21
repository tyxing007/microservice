package com.dengo.erp.repository.task;

import com.dengo.erp.model.task.Status;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Status repository hold database requests which process status entity. Created at 22.07.16
 *
 * @author Sem Babenko
 */
public interface StatusRepository extends JpaRepository<Status, Long>{
}
