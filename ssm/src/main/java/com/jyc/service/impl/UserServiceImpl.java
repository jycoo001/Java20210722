package com.jyc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.dao.UserDAO;
import com.jyc.model.User;
import com.jyc.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserDAO sqlDao;

	public User login(User t) {
		return sqlDao.login(t);
	}

	public int register(User t) {
		return sqlDao.register(t);
	}

	public int update(User t) {
		return sqlDao.update(t);
	}

	@Override
	public List<User> querySelector(User user) {
		return sqlDao.quertSelector(user);
	}

	@Override
	public int delete(Integer id) {
		return sqlDao.delete(id);
	}

	@Override
	public int deleteMany(Integer[] ids) {
		return sqlDao.deleteMany(ids);
	}
}
