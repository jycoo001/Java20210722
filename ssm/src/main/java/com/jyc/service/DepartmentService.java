package com.jyc.service;

import java.util.List;

import com.jyc.model.Department;

/**
 * 部门业务类,处理与部门有关的操作
 * 
 * @author 12430
 *
 */
public interface DepartmentService {

	/**
	 * 部门查询，需要部门实体
	 * 
	 * @param d    部门实体，可以为空，查全部
	 * @param page 页面类
	 * @return 返回查询到的集合，设置总查询的页数
	 */
	public List<Department> quertSelector(Department d);

	/**
	 * 查询单个部门实体
	 * 
	 * @param d
	 * @return
	 */
	public Department selectOne(Integer id);

	public List<Department> selectorMany(Integer[] ids);

	/**
	 * 添加操作
	 * 
	 * @param e 部门实体
	 * @return 返回受影响的行数
	 */
	public int insert(Department e);

	/**
	 * 更新操作,通过更新employee来更新department
	 * 
	 * @return 返回受影响的行数
	 */
	public int update();

	/**
	 * 更新操作，需要参数，name,id
	 * 
	 * @param d
	 * @return
	 */
	public int update(Department d);

	/**
	 * 删除操作
	 * 
	 * @param id 删除的id
	 * @return 返回受影响的行数
	 */
	public int delete(int id);

	public Object deleteMany(Integer[] id, List<Department> list);
}
