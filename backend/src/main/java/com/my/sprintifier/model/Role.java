package com.my.sprintifier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.my.sprintifier.model.base.AbstractBaseEntity;

@Entity
@Table(name="Role")
public class Role extends AbstractBaseEntity {

	@Column(name="name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
