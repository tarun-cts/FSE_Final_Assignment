package com.cognizant.fse.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;

import com.cognizant.fse.entity.Project;
@NoRepositoryBean
public interface ProjectDaoCustom {
	List<Project> getProjectsWithTaskSummary(Sort sort);
}
