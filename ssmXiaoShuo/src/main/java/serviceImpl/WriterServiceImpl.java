package serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.Writer;
import mapper.WriterMapper;
import service.WriterService;
import utils.MD5Utils;
import utils.SearchInfo;

@Service
public class WriterServiceImpl extends BaiscServiceImpl<Writer> implements WriterService {

	@Autowired
	WriterMapper mapper;

	public List<Writer> select() {
		SearchInfo info = new SearchInfo();
		info.toWhere("txt", "Writer.name", 1);
		return mapper.select(info);
	}

	public Writer selectByLibrary_id(int Library_id) {
		return mapper.selectByLibraryid(Library_id);
	}

	@Override
	public int insert(Writer u) {
		u.setLibrary_id(u.getLibrary_id()+1);
		u.setPassword(MD5Utils.MD5(u.getPassword()));
		return super.insert(u);
	}

	public void updatepass(Writer u) {
		mapper.update(u);
	}

	public Writer login(Writer u) {
		u.setPassword(MD5Utils.MD5(u.getPassword()));
		return mapper.login(u);
	}
}
