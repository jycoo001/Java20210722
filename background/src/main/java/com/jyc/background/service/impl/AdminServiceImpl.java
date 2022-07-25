package com.jyc.background.service.impl;

import com.jyc.background.mapper.AdminMapper;
import com.jyc.background.model.Admin;
import com.jyc.background.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    private AdminMapper adminMapper;

    @Autowired
    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public Admin login(Admin admin) {
        return adminMapper.login(admin);
    }

    @Override
    public Admin findByName(String name) {
        return adminMapper.findByName(name);
    }
}
