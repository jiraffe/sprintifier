package com.my.sprintifier.service.api;

import java.util.List;

import com.my.sprintifier.model.Project;
import com.my.sprintifier.model.Sprint;

public interface ProjectService extends AbstractService<Project> {

	public static final int WORK_WEEK_HOURS = 40;
	public static final int WORK_DAY_HOURS = 8;
	
	public Project getProjectWithMembers(Long id);

	public List<Sprint> getSprintCompilations(Long id);

	public String getSprintRecompilations(Long id, Long sprint);
	
}
