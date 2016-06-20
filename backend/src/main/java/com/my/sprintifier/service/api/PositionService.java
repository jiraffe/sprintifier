package com.my.sprintifier.service.api;

import java.util.Set;

import com.my.sprintifier.model.Position;
import com.my.sprintifier.model.User;

public interface PositionService extends AbstractService<Position>	{

	Set<User> getUsersByPosition(Long id);

}
