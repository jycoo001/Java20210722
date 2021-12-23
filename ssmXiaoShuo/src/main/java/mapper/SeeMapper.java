package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import entity.See;
import utils.SearchInfo;

@Repository
public interface SeeMapper extends BasicMapper<See> {

	
	@Select("select * from See ${where} ${limit} ")
	public List<See>  select (SearchInfo info);
	
	@Options(useGeneratedKeys=true, keyProperty="id")
	@Insert("insert into See (name) values(#{name})")
	public int insert(See u); 
	
	@Delete("delete from See where id=#{id}")
	public void delete(int id);
	
	@Update("update See set name=#{name} where id=#{id}")
	public void update(See u); 
	
	@Select("select * from See where id=#{id}")
	public See  selectById (int id);
	
}
