package com.my.sprintifier.service.api;

import java.util.Date;
import java.util.List;

import com.my.sprintifier.model.User;
import com.my.sprintifier.model.Vacation;

public interface DateContainer {

	List<Vacation> getList(Date from, Date to);
	List<Vacation> getList(User user);
}
