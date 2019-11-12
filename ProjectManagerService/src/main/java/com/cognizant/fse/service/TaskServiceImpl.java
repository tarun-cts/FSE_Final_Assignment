package com.cognizant.fse.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cognizant.fse.dao.TaskDao;
import com.cognizant.fse.entity.Task;

@Service("TaskService")
@Transactional
public class TaskServiceImpl extends AbstractService<Task> implements TaskService{
	
	@Autowired
	private TaskDao taskDao;

	public TaskServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CrudRepository<Task, Long> getDao() {
		// TODO Auto-generated method stub
		return taskDao;
	}
	public List<Task> findByProjectId(long projectId){
		return taskDao.findByProject(projectId);	
	}

}
