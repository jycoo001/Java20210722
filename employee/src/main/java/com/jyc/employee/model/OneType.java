package com.jyc.employee.model;

public class OneType {

	private Integer id;
	private String name;
	private TwoType twoType;

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

	public TwoType getTwoType() {
		return twoType;
	}

	public void setTwoType(TwoType twoType) {
		this.twoType = twoType;
	}

}
