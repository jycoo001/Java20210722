package com.jyc.background.service.impl;

import com.jyc.background.dao.MasterDAO;
import com.jyc.background.dao.PanDAO;
import com.jyc.background.model.Master;
import com.jyc.background.model.Pan;
import com.jyc.background.service.PanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class PanServiceImpl implements PanService {

    private PanDAO dao;
    private MasterDAO masterDAO;

    @Autowired
    public void setDao(PanDAO dao) {
        this.dao = dao;
    }

    @Autowired
    public void setMasterDAO(MasterDAO masterDAO) {
        this.masterDAO = masterDAO;
    }

    @Override
    public List<Pan> findAll(Pan pan) {
        return dao.findAll(pan);
    }

    @Override
    public List<Pan> findByQuestion(String question) {
        question = "%"+question+"%";
        return dao.findByQuestion(question);
    }

    @Override
    public int insert(Pan pan) {
        return dao.insert(pan);
    }

    @Override
    public int update(Pan pan, Master master) {
        String name = master.getName();
        Master master1 = masterDAO.findByName(name);
        pan.setMasterId(master1.getId());
        pan.setTime(Calendar.getInstance().getTime());
        return dao.update(pan);
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
        Pan pan = new Pan();
        pan.setId(id);
        List<Pan> list1 = dao.findAll(pan);
        Pan pan1 = list1.get(0);
        String isExam = pan1.getIsExam();
        if(isExam.equals("是")) {
            pan1.setIsExam("否");
        } else {
            pan1.setIsExam("是");
        }
        int row = dao.update(pan1);
        return row;
    }

    @Override
    public Pan findById(Integer id) {
        Pan pan = new Pan();
        pan.setId(id);
        List<Pan> list = dao.findAll(pan);
        Pan pan1 = list.get(0);
        return pan1;
    }
}
