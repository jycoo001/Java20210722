package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import entity.Admin;
import utils.SearchInfo;

@Repository
public interface AdminMapper extends BasicMapper<Admin> {

	@Select("select * from Admin ${where} ${limit} ")
	public List<Admin> select(SearchInfo info);

	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into Admin (name,password) values(#{name},#{password})")
	public int insert(Admin u);

	@Delete("delete from Admin where id=#{id}")
	public void delete(int id);

	@Update("update Admin set name=#{name},password=#{password} where id=#{id}")
	public void update(Admin u);

	@Select("select * from Admin where id=#{id}")
	public Admin selectById(int id);

	@Select("select * from Admin where name=#{name} and password=#{password}")
	public Admin login(Admin u);
}
