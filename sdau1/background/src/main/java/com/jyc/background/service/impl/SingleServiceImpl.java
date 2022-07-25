package com.jyc.background.service.impl;

import com.jyc.background.dao.MasterDAO;
import com.jyc.background.dao.SingleDAO;
import com.jyc.background.model.Master;
import com.jyc.background.model.Single;
import com.jyc.background.service.MasterService;
import com.jyc.background.service.SingleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class SingleServiceImpl implements SingleService {

    private SingleDAO dao;
    private MasterDAO masterDAO;

    @Autowired
    public void setDao(SingleDAO dao) {
        this.dao = dao;
    }

    @Autowired
    public void setMasterDAO(MasterDAO masterDAO) {
        this.masterDAO = masterDAO;
    }

    @Override
    public List<Single> findAll(Single single) {
        return dao.findAll(single);
    }

    @Override
    public List<Single> findByQuestion(String question) {
        question = "%"+question+"%";
        return dao.findByQuestion(question);
    }

    @Override
    public int insert(Single single) {
        return dao.insert(single);
    }

    @Override
    public int update(Single single, Master master) {
        String name = master.getName();
        Master master1 = masterDAO.findByName(name);
        single.setMasterId(master1.getId());
        single.setTime(Calendar.getInstance().getTime());
        return dao.update(single);
    }

    @Override
    public int delete(Integer id) {
        return dao.delete(id);
    }

    @Override
    public int deleteMany(Integer[] id) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < id.length; i++) {
            list.add(id[i]);
        }
        return dao.deleteMany(list);
    }

    @Override
    public int updateStatus(Integer id) {
        Single single = new Single();
        single.setId(id);
        List<Single> list1 = dao.findAll(single);
        Single single1 = list1.get(0);
        String isExam = single1.getIsExam();
        if(isExam.equals("是")) {
            single1.setIsExam("否");
        } else {
            single1.setIsExam("是");
        }
        int row = dao.update(single1);
        return row;
    }

    @Override
    public Single findById(Integer id) {
        Single single = new Single();
        single.setId(id);
        List<Single> list = dao.findAll(single);
        Single single1 = list.get(0);
        return single1;
    }
}
