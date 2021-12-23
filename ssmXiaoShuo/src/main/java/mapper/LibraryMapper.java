package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import entity.Library;
import utils.SearchInfo;

@Repository
public interface LibraryMapper extends BasicMapper<Library> {

	
	@Select("select * from Library ${where} ${limit} ")
	public List<Library>  select (SearchInfo info);
	
	@Options(useGeneratedKeys=true, keyProperty="id")
	@Insert("insert into Library (name,name_one,name_zhengwen) values(#{name},#{name_one},#{name_zhengwen})")
	public int insert(Library u); 
	
	@Delete("delete from Library where id=#{id}")
	public void delete(int id);
	
	@Update("update Library set name=#{name},name_one=#{name_one},name_zhengwen=#{name_zhengwen} where id=#{id}")
	public void update(Library u); 
	
	@Select("select * from Library where id=#{id}")
	public Library  selectById (int id);
	
}
