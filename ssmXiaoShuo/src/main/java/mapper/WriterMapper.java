package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import entity.Writer;
import utils.SearchInfo;

@Repository
public interface WriterMapper extends BasicMapper<Writer> {

	@Select("select Writer.*,Library.name Library_name "
			+ " from Writer inner join Library on Writer.Library_id=Library.id" + " ${where} ${limit} ")
	public List<Writer> select(SearchInfo info);

	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into Writer (name,password,Library_id) values(#{name},#{password},#{Library_id})")
	public int insert(Writer u);

	@Delete("delete from Writer where id=#{id}")
	public void delete(int id);

	@Update("update Writer set name=#{name},password=#{password},Library_id=#{Library_id} where id=#{id}")
	public void update(Writer u);

	@Select("select * from Writer where id=#{id}")
	public Writer selectById(int id);

	@Select("select * from Writer where Library_id=#{Library_id}")
	public Writer selectByLibraryid(int Library_id);

	@Select("select * from Writer where name=#{name} and password=#{password}")
	public Writer login(Writer u);
}
