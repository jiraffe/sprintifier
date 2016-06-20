package com.my.sprintifier.dao.impl;

import org.springframework.stereotype.Repository;

import com.my.sprintifier.dao.TeamMemberDao;
import com.my.sprintifier.model.TeamMember;

@Repository
public class TeamMemberDaoImpl extends AbstractDaoImpl<TeamMember> implements TeamMemberDao {

	public TeamMemberDaoImpl() {
		super(TeamMember.class);
	}
	
}