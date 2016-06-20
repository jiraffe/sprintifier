package com.my.sprintifier.dao.impl;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import com.my.sprintifier.dao.ProjectDao;
import com.my.sprintifier.model.Project;

@Repository
public class ProjectDaoImpl extends AbstractDaoImpl<Project> implements ProjectDao {

	public ProjectDaoImpl() {
		super(Project.class);
	}

	@Override
	public Project getProjectWithMembers(Long id) throws PersistenceException {
		
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq(ID, id));
		criteria.createCriteria("members", JoinType.LEFT_OUTER_JOIN);
		
		Project project = (Project) criteria.uniqueResult();
		return project;
	}
}