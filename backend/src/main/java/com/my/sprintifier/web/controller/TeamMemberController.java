package com.my.sprintifier.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.my.sprintifier.model.TeamMember;

@Controller
@RequestMapping(value="/teammembers")
public class TeamMemberController extends AbstractController<TeamMember> {

	public TeamMemberController() {
		super(TeamMember.class);
	}

}
