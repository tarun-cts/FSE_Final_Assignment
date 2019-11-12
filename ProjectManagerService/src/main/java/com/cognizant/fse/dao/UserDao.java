package com.cognizant.fse.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.fse.entity.User;

@Repository
public interface UserDao extends CrudRepository<User, Long>{

}
