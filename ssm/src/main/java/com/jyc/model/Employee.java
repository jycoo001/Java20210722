package com.jyc.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 员工model
 * 
 * @author 12430
 *
 */
public class Employee {
	private Integer id;
	private String name;
	private String sex;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	private Double salary;
	private Integer departmentId;
	private String picture;
	private Department department;
	private String fromTo;

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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		if (this.birthday != null) {
			return birthday;
		}
		return null;
	}

	public String getLocalBirthday() {
		if (this.birthday != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(this.birthday);
		}
		return null;
	}

	public void setBirthday(Date birthday) {
		if (birthday != null) {
			this.birthday = birthday;
		}
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;

	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String[] getFromTo() {
		if (fromTo != null && fromTo.trim().length() > 0) {
			return this.fromTo.split(" - ");
		}
		return null;
	}

	public String getFromTos() {
		return this.fromTo;
	}

	public void setFromTo(String fromTo) {
		this.fromTo = fromTo;
	}

	public String getStart() {
		if (this.fromTo != null && this.fromTo.trim().length() > 0) {
			return this.fromTo.split(" - ")[0];
		}
		return null;
	}

	public String getEnd() {
		if (this.fromTo != null && this.fromTo.trim().length() > 0) {
			return this.fromTo.split(" - ")[1];
		}
		return null;
	}

	public Boolean employee() {
		return (this.name != null && this.name.trim().length() > 0)
				&& (this.sex != null && this.sex.trim().length() > 0) && (this.birthday != null)
				&& (this.departmentId != null) ? true : false;
	}

}
