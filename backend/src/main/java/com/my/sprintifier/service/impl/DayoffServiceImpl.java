package com.my.sprintifier.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.my.sprintifier.model.Dayoff;
import com.my.sprintifier.service.api.DayoffService;

@Service
@Transactional
public class DayoffServiceImpl extends AbstractServiceImpl<Dayoff> implements DayoffService {

}