package com.jyc.background.service.impl;

import com.jyc.background.dao.MasterDAO;
import com.jyc.background.dao.MultiDAO;
import com.jyc.background.dao.SingleDAO;
import com.jyc.background.model.Master;
import com.jyc.background.model.Multi;
import com.jyc.background.model.Single;
import com.jyc.background.service.MultiService;
import com.jyc.background.service.SingleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class MultierviceImpl implements MultiService {

    private MultiDAO dao;
    private MasterDAO masterDAO;

    @Autowired
    public void setDao(MultiDAO dao) {
        this.dao = dao;
    }

    @Autowired
    public void setMasterDAO(MasterDAO masterDAO) {
        this.masterDAO = masterDAO;
    }

    @Override
    public List<Multi> findAll(Multi multi) {
        return dao.findAll(multi);
    }

    @Override
    public List<Multi> findByQuestion(String question) {
        question = "%"+question+"%";
        return dao.findByQuestion(question);
    }

    @Override
    public int insert(Multi multi) {
        return dao.insert(multi);
    }

    @Override
    public int update(Multi multi, Master master) {
        String name = master.getName();
        Master master1 = masterDAO.findByName(name);
        multi.setMasterId(master1.getId());
        multi.setTime(Calendar.getInstance().getTime());
        return dao.update(multi);
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
        Multi multi = new Multi();
        multi.setId(id);
        List<Multi> list1 = dao.findAll(multi);
        Multi multi1 = list1.get(0);
        String isExam = multi1.getIsExam();
        if(isExam.equals("是")) {
            multi1.setIsExam("否");
        } else {
            multi1.setIsExam("是");
        }
        int row = dao.update(multi1);
        return row;
    }

    @Override
    public Multi findById(Integer id) {
        Multi multi = new Multi();
        multi.setId(id);
        List<Multi> list = dao.findAll(multi);
        Multi multi1 = list.get(0);
        return multi1;
    }
}
