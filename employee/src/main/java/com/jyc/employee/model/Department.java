package com.jyc.employee.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 部门model
 * 
 * @author 12430
 *
 */
@SuppressWarnings("serial")
public class Department implements Serializable {
	private Integer id;
	private String name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startTime;
	private Integer count;
	private String fromTo;
	private String logo;
	private List<Employee> employees;
	private Address address;

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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getLocalStartTime() {
		if (this.startTime != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(this.startTime);
		}
		return null;
	}

	public String getFromTo() {
		return fromTo;
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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
