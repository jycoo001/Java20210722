package com.jyc.employee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jyc.employee.model.Address;

@Mapper
public interface AddressDAO {
	public List<Address> querySelector(Address address);

	public Address findById(Integer id);
}
