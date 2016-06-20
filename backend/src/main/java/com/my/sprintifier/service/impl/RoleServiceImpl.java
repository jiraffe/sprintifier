package com.my.sprintifier.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.my.sprintifier.model.Role;
import com.my.sprintifier.service.api.RoleService;

@Service
@Transactional
public class RoleServiceImpl extends AbstractServiceImpl<Role> implements RoleService {

}