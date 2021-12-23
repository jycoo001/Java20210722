package service;

import java.util.List;


public interface BasicService<T> {

	public List<T> select();

	public int insert(T u);

	public void delete(int id);

	public void update(T u);

	public T selectById(int id);

}
