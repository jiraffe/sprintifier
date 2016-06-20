package com.my.sprintifier.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.my.sprintifier.model.User;
import com.my.sprintifier.model.Vacation;
import com.my.sprintifier.service.api.DateContainer;

@Component
public class DateContainerImpl implements DateContainer {

	@Override
	public List<Vacation> getList(Date from, Date to) {
		return null;
	}

	@Override
	public List<Vacation> getList(User user) {
		return null;
	}

}
