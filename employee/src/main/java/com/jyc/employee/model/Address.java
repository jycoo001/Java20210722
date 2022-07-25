package com.jyc.employee.model;

public class Address {
	private Integer id;
	private String name;
	private String simpleName;
	private String selfName;
	private String selfShortName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSimpleName() {
		return simpleName;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}

	public String getSelfName() {
		return selfName;
	}

	public void setSelfName(String selfName) {
		this.selfName = selfName;
	}

	public String getSelfShortName() {
		return selfShortName;
	}

	public void setSelfShortName(String selfShortName) {
		this.selfShortName = selfShortName;
	}

}
