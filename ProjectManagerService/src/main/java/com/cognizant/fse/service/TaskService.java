package com.cognizant.fse.service;

import java.util.List;

import com.cognizant.fse.entity.Task;

public interface TaskService extends IOperations<Task> {
	List<Task> findByProjectId(long projectId);

}
