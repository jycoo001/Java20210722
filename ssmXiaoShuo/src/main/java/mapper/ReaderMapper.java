package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import entity.Reader;
import utils.SearchInfo;

@Repository
public interface ReaderMapper extends BasicMapper<Reader> {

	@Select("select * from Reader ${where} ${limit} ")
	public List<Reader> select(SearchInfo info);

	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into Reader (name,password) values(#{name},#{password})")
	public int insert(Reader u);

	@Delete("delete from Reader where id=#{id}")
	public void delete(int id);

	@Update("update Reader set name=#{name},password=#{password} where id=#{id}")
	public void update(Reader u);

	@Select("select * from Reader where id=#{id}")
	public Reader selectById(int id);

	@Select("select * from Reader where name=#{name} and password=#{password}")
	public Reader login(Reader u);
}
