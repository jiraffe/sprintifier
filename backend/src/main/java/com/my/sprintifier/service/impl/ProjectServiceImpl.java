package com.my.sprintifier.service.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.sprintifier.dao.DayoffDao;
import com.my.sprintifier.dao.ProjectDao;
import com.my.sprintifier.dao.VacationDao;
import com.my.sprintifier.model.Dayoff;
import com.my.sprintifier.model.Project;
import com.my.sprintifier.model.Sprint;
import com.my.sprintifier.model.TeamMember;
import com.my.sprintifier.model.Vacation;
import com.my.sprintifier.service.api.ProjectService;


@Service
@Transactional
public class ProjectServiceImpl extends AbstractServiceImpl<Project> implements ProjectService {

	@Autowired
	private VacationDao vacDao;
	@Autowired
	private DayoffDao dayoffDao;
	
	@Override
	public Project getProjectWithMembers(Long id) {
		return ((ProjectDao)dao).getProjectWithMembers(id);
	}

	/**
	 * need refactoring
	 */
	@Override
	public List<Sprint> getSprintCompilations(Long id) {
		
		Project project = getProjectWithMembers(id);
		
		// standard user SP/day
//			userStandardDaySp = 8 * userFocus * teamVelocity
		
		// user -> SP/day
		Map<Long, Integer> uToSp = new HashMap<>();
		
		// sum of ideal SP/day per all users at project 
		int idealDaySp = 0;

		for(TeamMember member: project.getMembers()) {
			Integer userStandardDaySp = WORK_DAY_HOURS * member.getFocus() * project.getTeamCapacity();
			uToSp.put(member.getUser().getId(), userStandardDaySp);
			idealDaySp += userStandardDaySp;
		}
		
		// ideal sprint SP = idealDaySp * sprint working days 
		

		int sp = project.getStoryPoints();
		Date sprintStart = project.getStartDate();
		List<Sprint> sprints = new ArrayList<>();
		
		while (sp > 0) {
			Instant sprintEnd = sprintStart.toInstant().plus(project.getSprintSize(), ChronoUnit.WEEKS);
			List<Dayoff> sprintDayoffs = dayoffDao.getByDates(sprintStart, Date.from(sprintEnd));

			Sprint sprint = new Sprint(sprintStart, Date.from(sprintEnd));
			
			int dayoffBalance = 0;
			for(Dayoff day: sprintDayoffs) {
				dayoffBalance += day.isHoliday() ? -1 : 1;
			}
			
			int sprintSP = idealDaySp * project.getSprintSize() * 5;
			if (sprintDayoffs.size() == 0 || dayoffBalance == 0) {
				sprint.setStoryPoints(sprintSP);
				sprints.add(sprint);
				sp -= sprint.getStoryPoints();
			    continue;
			}
			
//			Take into account unplanned work\no-work days
			sprintSP += idealDaySp * dayoffBalance;
			
//			Team members VACs (with focus and velocity of employee)
			List<Vacation> vacs = vacDao.getUsersVacList(uToSp.keySet(), sprintStart, Date.from(sprintEnd));
			for (Vacation vac: vacs) {
				sprintSP -= uToSp.get(vac.getUser().getId());
			}
			
			sprint.setStoryPoints(sprintSP);
			sprints.add(sprint);
			sp -= sprint.getStoryPoints();
		}
		return sprints;
	}

	@Override
	public String getSprintRecompilations(Long id, Long sprint) {
		// TODO Auto-generated method stub
		return null;
	}

}