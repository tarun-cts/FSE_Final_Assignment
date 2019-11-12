package com.cognizant.fse.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cognizant.fse.dao.ParentTaskDao;
import com.cognizant.fse.entity.ParentTask;

@Transactional
@Service("CommonSevice")
public class ParentTaskServiceImpl extends AbstractService<ParentTask> implements ParentTaskService {
	
	@Autowired
	private ParentTaskDao parentTaskDao;

	public ParentTaskServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CrudRepository<ParentTask, Long> getDao() {
		// TODO Auto-generated method stub
		return parentTaskDao;
	}

}
