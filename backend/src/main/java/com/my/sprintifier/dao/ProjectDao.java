package com.my.sprintifier.dao;

import javax.persistence.PersistenceException;

import com.my.sprintifier.model.Project;

public interface ProjectDao {

	Project getProjectWithMembers(Long id) throws PersistenceException;

}
