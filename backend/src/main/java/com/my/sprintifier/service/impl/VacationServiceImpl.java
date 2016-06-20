package com.my.sprintifier.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.my.sprintifier.model.Vacation;
import com.my.sprintifier.service.api.VacationService;

@Service
@Transactional
public class VacationServiceImpl extends AbstractServiceImpl<Vacation> implements VacationService {

}