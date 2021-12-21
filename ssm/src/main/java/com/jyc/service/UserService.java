package com.jyc.service;

import java.util.List;

import com.jyc.model.User;

public interface UserService {

	/**
	 * 查询，登录
	 * 
	 * @param t User实体
	 * @return 返回查找到的
	 */
	public User login(User t);

	/**
	 * 注册，添加
	 * 
	 * @param t User实体
	 * @return 返回受影响的行数
	 */
	public int register(User t);

	/**
	 * 更新操作，忘记密码操作
	 * 
	 * @param t User实体
	 * @return 返回受影响的行数
	 */
	public int update(User t);

	public List<User> querySelector(User user);

	public int delete(Integer id);

	public int deleteMany(Integer[] ids);
}
