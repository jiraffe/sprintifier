package com.my.sprintifier.web.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.sprintifier.model.Dayoff;

@Controller
@RequestMapping(value="/dayoffs")
public class DayoffController extends AbstractController<Dayoff> {

	public DayoffController() {
		super(Dayoff.class);
	}

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Map<Object, Object> getList() {
		return super.getList();
	}
	
}
