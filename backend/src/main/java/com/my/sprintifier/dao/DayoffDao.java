package com.my.sprintifier.dao;

import java.util.Date;
import java.util.List;

import com.my.sprintifier.model.Dayoff;

public interface DayoffDao {

	List<Dayoff> getByDates(Date sprintStart, Date from);

}
