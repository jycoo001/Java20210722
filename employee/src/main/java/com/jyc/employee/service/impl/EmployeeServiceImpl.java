package com.jyc.employee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.employee.dao.DepartmentDAO;
import com.jyc.employee.dao.EmployeeDAO;
import com.jyc.employee.model.Employee;
import com.jyc.employee.service.EmployeeService;

/**
 * employee对业务层进行操作
 * 
 * @author 12430
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	public EmployeeDAO sqlDao;

	/**
	 * department实例
	 */
	@Autowired
	private DepartmentDAO departmentDao;

	/**
	 * 员工查询操作
	 * 
	 * @param e    员工实体，可以为空
	 * @param page 页面
	 * @return 返回查询到的员工实体,分页
	 */
	public List<Employee> quertSelector(Employee e) {
		return sqlDao.quertSelector(e);
	}

	/**
	 * 员工查询单个
	 * 
	 * @param e 员工实体
	 * @return 返回一个员工实体
	 */
	public Employee selectorOne(Integer id) {
		return sqlDao.selectOne(id);
	}

	/**
	 * 添加，并更新department
	 * 
	 * @param e 员工实体
	 * @return 受影响的行数
	 */

	public int insert(Employee e) {
		int row1 = sqlDao.insert(e);
		int row2 = departmentDao.updateAll();
		if (row1 > 0 && row2 > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * 修改表，并更新department
	 * 
	 * @param e employee实体
	 * @return 返回受影响的行数
	 */
	public int update(Employee e) {
		int row1 = sqlDao.update(e);
		int row2 = departmentDao.updateAll();
		if (row1 > 0 && row2 > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * 删除，并更新department
	 * 
	 * @param id 要删除的id
	 * @return 返回受影响的行数
	 */

	public int delete(Integer id) {
		int row = sqlDao.delete(id);
		int row1 = departmentDao.updateAll();
		if (row1 > 0 && row > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * 删除多条记录，更新department
	 * 
	 * @param obj 删除的id列表
	 * @return 返回受影响的行数
	 */
	public int deleteMany(Integer[] obj) {
		int row = sqlDao.deleteMany(obj);
		int row2 = departmentDao.updateAll();
		if (row2 > 0 && row > 0) {
			return row;
		} else {
			return 0;
		}
	}

	@Override
	public int updateMany(List<Employee> list) {
		return sqlDao.updateMany(list);
	}

}
