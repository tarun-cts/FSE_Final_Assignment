package com.cognizant.fse.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.cognizant.fse.entity.Project;

public interface ProjectService extends IOperations<Project> {
	
	List<Project> getProjectsWithTaskSummary(Sort sort);

}
