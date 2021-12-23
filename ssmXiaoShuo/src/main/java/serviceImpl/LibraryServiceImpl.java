package serviceImpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.Library;
import mapper.LibraryMapper;
import service.LibraryService;
import utils.SearchInfo;

@Service
public class LibraryServiceImpl extends BaiscServiceImpl<Library> implements LibraryService{

	@Autowired
	LibraryMapper mapper;
	
	public List<Library> select() {
		SearchInfo info= new SearchInfo();
		info.toWhere("txt","Library.name", 1);
		return mapper.select(info);
	}

	

	
	
}
