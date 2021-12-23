package serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.Admin;
import mapper.AdminMapper;
import service.AdminService;
import utils.MD5Utils;
import utils.SearchInfo;

@Service
public class AdminServiceImpl extends BaiscServiceImpl<Admin> implements AdminService{

	@Autowired
	AdminMapper mapper;
	
	public List<Admin> select() {
		SearchInfo info= new SearchInfo();
		info.toWhere("txt","Admin.name", 1);
		return mapper.select(info);
	}

	@Override
	public int insert(Admin u) {
		u.setPassword(MD5Utils.MD5(u.getPassword()));
		return super.insert(u);
	}

	public void updatepass(Admin u) {
		mapper.update(u);
	}

	public Admin login(Admin u) {
		u.setPassword(MD5Utils.MD5(u.getPassword()));
		return mapper.login(u);
	}
	
}
