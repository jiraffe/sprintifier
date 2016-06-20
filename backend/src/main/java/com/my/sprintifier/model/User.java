package com.my.sprintifier.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.my.sprintifier.model.base.AbstractBaseEntity;

@Entity
@Table(name="User")
public class User extends AbstractBaseEntity {

	@Column(name="name")
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Position.class)
	@JoinColumn(name = "POSITION_FK")
	private Position position;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Vacation> vacs = new HashSet<>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Position getPosition() {
		return position ;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Set<Vacation> getVacs() {
		return vacs;
	}

	public void setVacs(Set<Vacation> vacs) {
		this.vacs = vacs;
	}
}
