package com.my.sprintifier.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.sprintifier.model.User;
import com.my.sprintifier.service.api.PositionService;
import com.my.sprintifier.web.util.ResponseMapUtil;

@Controller
@RequestMapping(value="/users")
public class UserController extends AbstractController<User> {
	
	@Autowired
	private PositionService positionService;
	
	public UserController() {
		super(User.class);
	}
	
	@RequestMapping(value="/position/{id}")
	public @ResponseBody Map<Object, Object> getUsersByRole(@PathVariable Long id) {
		return ResponseMapUtil.mapSuccess(positionService.getUsersByPosition(id));
	}

}
