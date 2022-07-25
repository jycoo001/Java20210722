package com.jyc.forward.service;

import com.jyc.forward.model.UserPractice;

import java.util.Map;

public interface UserPracticeService {
    public void findByQuestion(String uuid, Map<String,Object> map);
}
