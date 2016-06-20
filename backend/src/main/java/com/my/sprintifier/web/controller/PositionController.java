package com.my.sprintifier.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.my.sprintifier.model.Position;

@Controller
@RequestMapping(value="/positions")
public class PositionController extends AbstractController<Position>	{

	public PositionController() {
		super(Position.class);
	}

}
