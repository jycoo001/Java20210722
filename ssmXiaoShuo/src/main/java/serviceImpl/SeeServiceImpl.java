package serviceImpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.See;
import mapper.SeeMapper;
import service.SeeService;
import utils.SearchInfo;

@Service
public class SeeServiceImpl extends BaiscServiceImpl<See> implements SeeService{

	@Autowired
	SeeMapper mapper;
	
	public List<See> select() {
		SearchInfo info= new SearchInfo();
		info.toWhere("txt","See.name", 1);
		return mapper.select(info);
	}

	

	
	
}
