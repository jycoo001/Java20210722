package com.jyc.forward.service;

import com.jyc.forward.model.Exam;
import com.jyc.forward.model.UserExam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface ExamService {
    //查看未考的
    public List<UserExam> find(Integer userId);

    //去做题
    public void toDoing(String uuid, Map<String ,Object> map, HttpSession session);
    //做题
    public int save(String single,String multi,String pan,List<Integer> listSin,List<Integer> listMu,List<Integer> listPa, Integer userId, String uuid);

    //查看已考的
    public List<UserExam> findHis(Integer userId);

    public void findHisExam(String uuid,Map<String,Object> map);

}
