package com.my.sprintifier.dao;

import java.util.Set;

import com.my.sprintifier.model.User;

public interface PositionDao {

	Set<User> getUsersByPosition(Long id);

}
