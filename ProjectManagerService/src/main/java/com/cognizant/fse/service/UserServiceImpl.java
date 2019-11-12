package com.cognizant.fse.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cognizant.fse.dao.UserDao;
import com.cognizant.fse.entity.User;

@Service("UserService")
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
	
	@Autowired
	private UserDao userDao;

	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CrudRepository<User, Long> getDao() {
		// TODO Auto-generated method stub
		return userDao;
	}

}
