package com.my.sprintifier.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.my.sprintifier.dao.DayoffDao;
import com.my.sprintifier.model.Dayoff;

@Repository
public class DayoffDaoImpl extends AbstractDaoImpl<Dayoff> implements DayoffDao {

	public DayoffDaoImpl() {
		super(Dayoff.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dayoff> getByDates(Date from, Date to) {
		
		Criteria cr = createCriteria();
		cr.add(Restrictions.between("day", from, to));
		return cr.list();
	}

}