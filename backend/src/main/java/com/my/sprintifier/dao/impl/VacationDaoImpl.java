package com.my.sprintifier.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import com.my.sprintifier.dao.VacationDao;
import com.my.sprintifier.model.User;
import com.my.sprintifier.model.Vacation;

@Repository
public class VacationDaoImpl extends AbstractDaoImpl<Vacation> implements VacationDao {

	public VacationDaoImpl() {
		super(Vacation.class);
	}

	@Override
	public List<Vacation> getList(Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vacation> getList(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Vacation> getUsersVacList(Set<Long> userIds, Date from, Date to) {
		Criteria cr = createCriteria();
		
		cr.add(Restrictions.between("day", from, to));
		cr.createCriteria("user", "u", JoinType.LEFT_OUTER_JOIN);
		
		cr.add(Restrictions.in("u.id", userIds));
		
		return cr.list();
	}

}