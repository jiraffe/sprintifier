package com.my.sprintifier.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.my.sprintifier.model.base.AbstractBaseEntity;

@Entity
@Table(name="PROJECT")
public class Project extends AbstractBaseEntity {

	
	@Column(name="name")
	private String name;
	
	@Column(name="startDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	/**
	 * count of weeks for one sprint
	 */
	@Column(name="sprintSize")
	private Integer sprintSize;
	
	/**
	 * team velocity
	 */
	@Column(name="teamCapacity")
	private Integer teamCapacity;
	
	/**
	 * total planned story points for project
	 */
	@Column(name="storyPoints")
	private Integer storyPoints;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	private Set<TeamMember> members = new HashSet<>();

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * count of weeks for one sprint
	 */
	public Integer getSprintSize() {
		return sprintSize;
	}
	/**
	 * count of weeks for one sprint
	 */
	public void setSprintSize(Integer sprintSize) {
		this.sprintSize = sprintSize;
	}
	/**
	 * team velocity
	 */
	public Integer getTeamCapacity() {
		return teamCapacity;
	}
	/**
	 * team velocity
	 */
	public void setTeamCapacity(Integer teamCapacity) {
		this.teamCapacity = teamCapacity;
	}

	public Set<TeamMember> getMembers() {
		return members;
	}
	public void setMembers(Set<TeamMember> members) {
		this.members = members;
	}
	public Integer getStoryPoints() {
		return storyPoints;
	}
	public void setStoryPoints(Integer storyPoints) {
		this.storyPoints = storyPoints;
	}
}
