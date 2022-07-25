package com.jyc.forward.service;

import com.jyc.forward.model.Pan;
import com.jyc.forward.model.Question;

import java.util.List;
import java.util.Map;

public interface TestService {
    public void sel(Question question, Map<String,Object> map,List<Integer> listSingle,List<Integer> listMu,List<Integer> listPan);

    public int save(String single,String multi,String pan,List<Integer> listSin,List<Integer> listMu,List<Integer> listPa, Integer userId, String uuid);
}
