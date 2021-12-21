package com.jyc.service;

import java.util.List;

import com.jyc.model.Employee;

/**
 * employee对DAO层进行操作
 * 
 * @author 12430
 *
 */
public interface EmployeeService {

	/**
	 * 查询多个
	 * 
	 * @param e
	 * @param page
	 * @return
	 */
	public List<Employee> quertSelector(Employee e);

	/**
	 * 员工查询单个
	 * 
	 * @param e 员工实体
	 * @return 返回一个员工实体
	 */
	public Employee selectorOne(Integer id);

	/**
	 * 添加，并更新department
	 * 
	 * @param e 员工实体
	 * @return 受影响的行数
	 */

	public int insert(Employee e);

	/**
	 * 修改表，并更新department
	 * 
	 * @param e employee实体
	 * @return 返回受影响的行数
	 */
	public int update(Employee e);

	public int updateMany(List<Employee> list);

	/**
	 * 删除，并更新department
	 * 
	 * @param id 要删除的id
	 * @return 返回受影响的行数
	 */
	public int delete(Integer id);

	/**
	 * 删除多条记录，更新department
	 * 
	 * @param obj 删除的id列表
	 * @return 返回受影响的行数
	 */
	public int deleteMany(Integer[] obj);
}
