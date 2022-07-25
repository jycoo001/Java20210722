package com.jyc.employee.model;

public class TwoType {

	private Integer id;
	private String name;
	private Integer parentId;
	private ThreeType threeTypes;

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

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public ThreeType getThreeType() {
		return threeTypes;
	}

	public void setThreeType(ThreeType threeTypes) {
		this.threeTypes = threeTypes;
	}

}
