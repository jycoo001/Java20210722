package com.jyc.background.service;

import com.jyc.background.model.Master;
import com.jyc.background.model.Pan;

import java.util.List;

public interface PanService {
    public List<Pan> findAll(Pan pan);

    public List<Pan> findByQuestion(String question);

    public int insert(Pan Pan);

    public int update(Pan pan, Master master);

    public int delete(Integer id);

    public int deleteMany(Integer[] id);

    public int updateStatus(Integer id);

    public Pan findById(Integer id);
}
