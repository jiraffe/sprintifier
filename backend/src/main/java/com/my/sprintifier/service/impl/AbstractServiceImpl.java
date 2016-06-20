package com.my.sprintifier.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.my.sprintifier.dao.impl.AbstractDaoImpl;
import com.my.sprintifier.model.base.AbstractBaseEntity;
import com.my.sprintifier.service.api.AbstractService;

public class AbstractServiceImpl<T extends AbstractBaseEntity> implements AbstractService<T> {

	@Autowired
	protected AbstractDaoImpl<T> dao;
	
	@Transactional
	public Long add(T record) {
		return dao.addRecord(record);
	}
	
	@Transactional
	public List<T> get() {
		return dao.getAll();
	}
	
	@Transactional
	public T get(Long id)	{
		return dao.getRecordById(id);
	}
	
	@Transactional
	public void update(T record)	{
		dao.updateRecord(record);
	}
	
	@Transactional
	public void delete(T record) {
		dao.deleteRecord(record.getId());
	}
	
	@Transactional
	public void delete(Long id) {
		dao.deleteRecord(id);
	}
}
