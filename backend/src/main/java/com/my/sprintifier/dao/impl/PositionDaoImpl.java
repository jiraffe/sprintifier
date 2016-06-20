package com.my.sprintifier.dao.impl;

import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.my.sprintifier.dao.PositionDao;
import com.my.sprintifier.model.Position;
import com.my.sprintifier.model.User;

@Repository
public class PositionDaoImpl extends AbstractDaoImpl<Position> implements PositionDao {

	public PositionDaoImpl() {
		super(Position.class);
	}

	@Override
	public Set<User> getUsersByPosition(Long id) {
		
		Criteria cr = createCriteria();
		cr.add(Restrictions.eq(ID, id));
		cr.setFetchMode("users", FetchMode.JOIN);
		
		Position pos = (Position) cr.uniqueResult();
		
		return pos.getUsers();
	}

}
