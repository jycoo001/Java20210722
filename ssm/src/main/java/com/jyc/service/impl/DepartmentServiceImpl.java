package com.jyc.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.dao.DepartmentDAO;
import com.jyc.model.Department;
import com.jyc.service.DepartmentService;

/**
 * 部门业务类,处理与部门有关的操作
 * 
 * @author 12430
 *
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	public DepartmentDAO sqlDao;

	public List<Department> quertSelector(Department d) {
		return sqlDao.quertSelector(d);
	}

	/**
	 * 查询单个部门实体
	 * 
	 * @param d
	 * @return
	 */
	public Department selectOne(Integer id) {
		return sqlDao.selectorOne(id);
	}

	/**
	 * 添加操作
	 * 
	 * @param e 部门实体
	 * @return 返回受影响的行数
	 */
	public int insert(Department d) {
		d.setCount(0);
		Calendar calendar = Calendar.getInstance();
		d.setStartTime(calendar.getTime());
		return sqlDao.insert(d);
	}

	/**
	 * 更新操作,通过更新employee来更新department
	 * 
	 * @return 返回受影响的行数
	 */
	public int update() {

		return sqlDao.updateAll();
	}

	/**
	 * 更新操作，需要参数，name,id
	 * 
	 * @param d
	 * @return
	 */
	public int update(Department d) {

		return sqlDao.update(d);
	}

	/**
	 * 删除操作
	 * 
	 * @param id 删除的id
	 * @return 返回受影响的行数
	 */
	public int delete(int id) {
		return sqlDao.delete(id);
	}

	/**
	 * 删除多条记录，更新department
	 * 
	 * @param obj 删除的id列表
	 * @return 返回受影响的行数
	 */
	public Object deleteMany(Integer[] obj, List<Department> list) {
		boolean b = true;
		for (Department d : list) {
			if (d.getCount() <= 0) {
				b = false;
			}
		}
		if (!b) {
			int row = sqlDao.deleteMany(obj);
			if (row > 0) {
				return row;
			} else {
				return "删除失败！";
			}
		} else {
			return "删除的部门人数不为空！";
		}
	}

	@Override
	public List<Department> selectorMany(Integer[] ids) {
		return sqlDao.selectorMany(ids);
	}
}
