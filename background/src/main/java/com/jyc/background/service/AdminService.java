package com.jyc.background.service;

import com.jyc.background.model.Admin;

public interface AdminService {
    public Admin login(Admin admin);

    public Admin findByName(String name);
}
