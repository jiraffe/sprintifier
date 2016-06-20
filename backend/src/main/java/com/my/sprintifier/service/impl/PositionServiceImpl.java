package com.my.sprintifier.service.impl;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.my.sprintifier.dao.PositionDao;
import com.my.sprintifier.model.Position;
import com.my.sprintifier.model.User;
import com.my.sprintifier.service.api.PositionService;

@Service
@Transactional
public class PositionServiceImpl extends AbstractServiceImpl<Position> implements PositionService	{

	@Override
	public Set<User> getUsersByPosition(Long id) {
		return ((PositionDao) dao).getUsersByPosition(id);
	}

}
