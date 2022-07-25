package com.jyc.employee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jyc.employee.model.Department;

@Mapper
public interface DepartmentDAO {
	public List<Department> quertSelector(Department d);

	public Department selectorOne(Integer id);

	public int insert(Department d);

	public int updateAll();

	public int update(Department d);

	public int delete(Integer id);

	public int deleteMany(@Param(value = "ids") Integer[] ids);

	public List<Department> selectorMany(@Param(value = "ids") Integer[] ids);

}
