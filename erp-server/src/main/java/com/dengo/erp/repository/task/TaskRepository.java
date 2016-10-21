package com.dengo.erp.repository.task;

import com.dengo.erp.model.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Set;

/**
 * Repository holds all request to database which depend on task entity. Created at 22.07.16
 *
 * @author Sem Babenko
 */

public interface TaskRepository extends JpaRepository<Task, Long>{
    @Query(value = "SELECT * FROM TASK WHERE PROJECT_ID = ?1", nativeQuery = true)
    public Set<Task> findTasksByProjectId(Long Id);
}
