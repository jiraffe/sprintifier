package com.my.sprintifier.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.my.sprintifier.model.TeamMember;
import com.my.sprintifier.service.api.TeamMemberService;

@Service
@Transactional
public class TeamMemberServiceImpl extends AbstractServiceImpl<TeamMember> implements TeamMemberService {

}