package com.my.sprintifier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.my.sprintifier.model.base.AbstractBaseEntity;

@Entity
@Table(name="TEAM_MEMBER")
public class TeamMember extends AbstractBaseEntity {

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Project.class)
	@JoinColumn(name = "project_fk")
	private Project project;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
	@JoinColumn(name = "user_fk")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Position.class)
	@JoinColumn(name = "position_fk")
	private Position position;
	
	@Column(name="focus")
	private Integer focus;
	
	@Column(name="note")
	private String note;

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getFocus() {
		return focus;
	}

	public void setFocus(Integer focus) {
		this.focus = focus;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
}
