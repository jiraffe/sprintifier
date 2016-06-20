package com.my.sprintifier.service.api;

import java.util.List;

import com.my.sprintifier.model.base.AbstractBaseEntity;

public interface AbstractService<T extends AbstractBaseEntity> {

	public Long add(T record);
	
	public List<T> get();
	public T get(Long id);
	
	public void update(T record);
	
	public void delete(T record);
	public void delete(Long id);
	
}
