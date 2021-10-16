package com.jyc.mapper;

import com.jyc.entity.User;

public interface UserMapper {
    public User login(User user);
    public void register(User user);
    public User selectAll(String name);

}
