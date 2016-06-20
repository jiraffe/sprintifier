package com.my.sprintifier.web.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.sprintifier.model.Vacation;
import com.my.sprintifier.web.util.ResponseMapUtil;

@Controller
@RequestMapping(value="/vacations")
public class VacationController extends AbstractController<Vacation> {

	public VacationController() {
		super(Vacation.class);
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Map<Object, Object> getList() {
		return ResponseMapUtil.mapSuccess(service.get());
	}
	
}
