package com.dengo.erp.repository.task;

import com.dengo.erp.model.task.Label;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Label repository hold database requests which process label entity. Created at 22.07.16
 * @author Sem Babenko
 */
public interface LabelRepository extends JpaRepository<Label, Long>{
}
