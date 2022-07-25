package com.jyc.background.service;

import com.jyc.background.model.Master;
import com.jyc.background.model.Multi;
import com.jyc.background.model.Single;

import java.util.List;

public interface MultiService {
    public List<Multi> findAll(Multi multi);

    public List<Multi> findByQuestion(String question);

    public int insert(Multi multi);

    public int update(Multi multi, Master master);

    public int delete(Integer id);

    public int deleteMany(Integer[] id);

    public int updateStatus(Integer id);

    public Multi findById(Integer id);
}
