package serviceImpl;

import java.lang.reflect.Field;
import java.util.List;

import mapper.BasicMapper;
import utils.SearchInfo;

public class BaiscServiceImpl<T> {

	private BasicMapper<T> getmapper(){
		try {
		Field f=this.getClass().getDeclaredField("mapper");
		f.setAccessible(true);
		return (BasicMapper<T>) f.get(this);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<T> select() {
		SearchInfo info= new SearchInfo();
		return getmapper().select(info);
	}

	public int insert(T u) {
		
		return getmapper().insert(u);
	}

	public void delete(int id) {
		getmapper().delete(id);
	}

	public void update(T u) {
		getmapper().update(u);
	}

	public T selectById(int id) {
		return getmapper().selectById(id);
	}

	
}
