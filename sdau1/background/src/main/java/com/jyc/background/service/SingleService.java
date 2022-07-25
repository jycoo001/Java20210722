package com.jyc.background.service;

import com.jyc.background.model.Master;
import com.jyc.background.model.Single;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SingleService {
    public List<Single> findAll(Single single);

    public List<Single> findByQuestion(String question);

    public int insert(Single single);

    public int update(Single single, Master master);

    public int delete(Integer id);

    public int deleteMany(Integer[] id);

    public int updateStatus(Integer id);

    public Single findById(Integer id);
}
