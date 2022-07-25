package com.jyc.employee.dao;

import java.util.List;

import com.jyc.employee.model.Cart;

public interface CartDAO extends BaseDAO {

	public List<Cart> findByUserId(Integer id);

	public List<Cart> findByGoodsId(Integer id);
}
