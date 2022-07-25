package com.jyc.employee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BaseDAO {

	public <T> List<T> findAll(T t);

	public <T> T findById(Integer id);

	public <T> int insert(T t);

	public <T> int update(T t);

	public <T> int delete(Integer id);

	public <T> int deleteMany(@Param(value = "ids") Integer[] ids);
}
