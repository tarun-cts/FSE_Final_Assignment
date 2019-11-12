package com.cognizant.fse.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.fse.entity.ParentTask;

@Repository
public interface ParentTaskDao extends CrudRepository<ParentTask, Long> {

}
