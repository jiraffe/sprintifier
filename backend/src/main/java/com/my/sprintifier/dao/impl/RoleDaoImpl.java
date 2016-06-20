package com.my.sprintifier.dao.impl;

import org.springframework.stereotype.Repository;

import com.my.sprintifier.dao.RoleDao;
import com.my.sprintifier.model.Role;

@Repository
public class RoleDaoImpl extends AbstractDaoImpl<Role> implements RoleDao {

	public RoleDaoImpl() {
		super(Role.class);
	}

}