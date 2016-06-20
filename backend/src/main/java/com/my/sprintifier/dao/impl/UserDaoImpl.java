package com.my.sprintifier.dao.impl;

import org.springframework.stereotype.Repository;

import com.my.sprintifier.dao.UserDao;
import com.my.sprintifier.model.User;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao {

	public UserDaoImpl() {
		super(User.class);
	}

}