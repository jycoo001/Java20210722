package serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.Reader;
import mapper.ReaderMapper;
import service.ReaderService;
import utils.MD5Utils;
import utils.SearchInfo;

@Service
public class ReaderServiceImpl extends BaiscServiceImpl<Reader> implements ReaderService {

	@Autowired
	ReaderMapper mapper;

	public List<Reader> select() {
		SearchInfo info = new SearchInfo();
		info.toWhere("txt", "Reader.name", 1);
		return mapper.select(info);
	}

	@Override
	public int insert(Reader u) {
		u.setPassword(MD5Utils.MD5(u.getPassword()));
		return super.insert(u);
	}
	public void updatepass(Reader u) {
		mapper.update(u);
	}

	public Reader login(Reader u) {
		u.setPassword(MD5Utils.MD5(u.getPassword()));
		return mapper.login(u);
	}
}
