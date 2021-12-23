package mapper;

import java.util.List;


import utils.SearchInfo;

public interface BasicMapper<T> {
	
	public List<T> select(SearchInfo info);

	public int insert(T u);

	public void delete(int id);

	public void update(T u);

	public T selectById(int id);
}
