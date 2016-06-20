package com.my.sprintifier.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.my.sprintifier.model.User;
import com.my.sprintifier.service.api.UserService;

@Service
@Transactional
public class UserServiceImpl extends AbstractServiceImpl<User> implements UserService {

}