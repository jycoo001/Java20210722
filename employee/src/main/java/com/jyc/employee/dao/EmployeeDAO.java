package com.jyc.employee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jyc.employee.model.Employee;

@Mapper
public interface EmployeeDAO {

	public List<Employee> quertSelector(Employee e);

	public Employee selectOne(Integer id);

	public Employee selectByDepartmentId(Integer id);

	public int selectCounts(String sql, List<Object> list);

	public int insert(Employee e);

	public int update(Employee e);

	public int updateMany(@Param(value = "employeeList") List<Employee> list);

	public int delete(Integer id);

	public int deleteMany(@Param(value = "ids") Integer[] ids);
}
