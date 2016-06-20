package com.my.sprintifier.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.my.sprintifier.model.base.AbstractBaseEntity;

@Entity
@Table(name="DayOff")
public class Dayoff extends AbstractBaseEntity {

	private Date day;
	
	private boolean isHoliday;

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public boolean isHoliday() {
		return isHoliday;
	}

	public void setHoliday(boolean isHoliday) {
		this.isHoliday = isHoliday;
	}
}