package com.jyc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jyc.model.User;

@Mapper
public interface UserDAO {
	public User login(User u);

	public int register(User u);

	public int update(User u);

	public int delete(Integer id);

	public int deleteMany(@Param(value = "ids") Integer[] ids);

	public List<User> quertSelector(User u);
}
