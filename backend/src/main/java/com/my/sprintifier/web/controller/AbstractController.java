package com.my.sprintifier.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.sprintifier.model.base.AbstractBaseEntity;
import com.my.sprintifier.service.api.AbstractService;
import com.my.sprintifier.web.util.ResponseMapUtil;

import net.sf.json.JSONObject;

public class AbstractController<T extends AbstractBaseEntity> {

	@Autowired
	protected AbstractService<T> service;
	
	private Class<T> type;
	
	public AbstractController(Class<T> type) {
        this.type = type;
   }

   protected Class<T> getType() {
       return this.type;
   }
	
   @SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody Map<Object, Object> save(@RequestBody Object data) {
		
		JSONObject json = JSONObject.fromObject(data);
		T record = (T) JSONObject.toBean(json, type);
		
		service.add(record);
		
		return ResponseMapUtil.mapSuccess();
	}
   
   @SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Map<Object, Object> update(@RequestBody Object data) {
		
		JSONObject json = JSONObject.fromObject(data);
		T record = (T) JSONObject.toBean(json, type);
		
		service.update(record);
		
		return ResponseMapUtil.mapSuccess();
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Map<Object, Object> getList() {
		return ResponseMapUtil.mapSuccess(service.get());
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Map<Object, Object> getRecord(@PathVariable Long id) {
		return ResponseMapUtil.mapSuccess(service.get(id));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Map<Object, Object> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseMapUtil.mapSuccess();
	}
}
