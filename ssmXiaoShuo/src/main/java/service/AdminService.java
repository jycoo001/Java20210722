package service;

import entity.Admin;

public interface AdminService extends BasicService<Admin> {

	public void updatepass(Admin u);

	public Admin login(Admin u);
}
