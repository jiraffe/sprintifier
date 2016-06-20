package com.my.sprintifier.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.my.sprintifier.model.User;
import com.my.sprintifier.model.Vacation;

public interface VacationDao {

	List<Vacation> getList(Date from, Date to);
	List<Vacation> getList(User user);
	List<Vacation> getUsersVacList(Set<Long> users, Date from, Date to);
	
}
