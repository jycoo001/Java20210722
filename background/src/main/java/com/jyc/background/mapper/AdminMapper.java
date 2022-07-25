package com.jyc.background.mapper;

import com.jyc.background.model.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    public Admin login(Admin admin);

    public Admin findByName(String name);
}
