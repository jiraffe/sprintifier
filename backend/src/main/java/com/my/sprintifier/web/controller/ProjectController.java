package com.my.sprintifier.web.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.sprintifier.model.Project;
import com.my.sprintifier.service.api.ProjectService;
import com.my.sprintifier.web.util.ResponseMapUtil;

@Controller
@RequestMapping(value="/projects")
public class ProjectController extends AbstractController<Project> {

	public ProjectController() {
		super(Project.class);
	}
	
	@RequestMapping(value = "/{id}/members", method = RequestMethod.GET)
	public @ResponseBody Map<Object, Object> getProjectWithMembers(@PathVariable Long id) {
		return ResponseMapUtil.mapSuccess(((ProjectService)service).getProjectWithMembers(id));
	}
	
	@RequestMapping(value = "/{id}/calulate", method = RequestMethod.GET)
	public @ResponseBody Map<Object, Object> getSprintCompilations(@PathVariable Long id) {
		return ResponseMapUtil.mapSuccess(((ProjectService)service).getSprintCompilations(id));
	}
	
	@RequestMapping(value = "/{id}/recalulate/from/{sprint}", method = RequestMethod.GET)
	public @ResponseBody Map<Object, Object> getSprintRecompilations(@PathVariable Long id, @PathVariable Long sprint) {
		return ResponseMapUtil.mapSuccess(((ProjectService)service).getSprintRecompilations(id, sprint));
	}
	
}