package com.my.sprintifier.model;

import java.util.Date;

public class Sprint {

	private Date start;
	private Date end;
	private int storyPoints;
	
	
	public Sprint(Date start, Date end) {
		super();
		this.start = start;
		this.end = end;
	}

	public Sprint(Date start, Date end, int storyPoints) {
		super();
		this.start = start;
		this.end = end;
		this.storyPoints = storyPoints;
	}
	
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public int getStoryPoints() {
		return storyPoints;
	}
	public void setStoryPoints(int storyPoints) {
		this.storyPoints = storyPoints;
	}
}
