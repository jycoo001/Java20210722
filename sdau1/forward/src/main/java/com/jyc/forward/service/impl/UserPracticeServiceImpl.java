package com.jyc.forward.service.impl;

import com.jyc.forward.dao.MultiDAO;
import com.jyc.forward.dao.PanDAO;
import com.jyc.forward.dao.SingleDAO;
import com.jyc.forward.dao.UserPracticeDAO;
import com.jyc.forward.model.*;
import com.jyc.forward.service.UserPracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserPracticeServiceImpl implements UserPracticeService {
    private UserPracticeDAO dao;
    @Autowired
    public void setDao(UserPracticeDAO dao) {
        this.dao = dao;
    }
    private SingleDAO singleDAO;
    @Autowired
    public void setSingleDAO(SingleDAO singleDAO) {
        this.singleDAO = singleDAO;
    }
    private MultiDAO multiDAO;
    @Autowired
    public void setMultiDAO(MultiDAO multiDAO) {
        this.multiDAO = multiDAO;
    }
    private PanDAO panDAO;
    @Autowired
    public void setPanDAO(PanDAO panDAO) {
        this.panDAO = panDAO;
    }


    @Override
    public void findByQuestion(String uuid, Map<String,Object> map) {
        UserPractice userPractice = dao.findByUUId(uuid);
        List<Practice> list = userPractice.getPractice();
        List<SingleLook> list1 = new ArrayList<>();
        List<MultiLook> list2 = new ArrayList<>();
        List<PanLook> list3 = new ArrayList<>();
        List<Integer> sin = new ArrayList<>();
        List<Integer> mul = new ArrayList<>();
        List<Integer> pan = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            Practice practice = list.get(i);
            String type = practice.getType();
            if(type.equals("单选")) {
                sin.add(practice.getQuestionId());
                SingleLook singleLook = new SingleLook();
                singleLook.setUserSelect(practice.getUserSelect());
                list1.add(singleLook);
            } else if(type.equals("多选")) {
                mul.add(practice.getQuestionId());
                MultiLook multiLook = new MultiLook();
                multiLook.setUserSelect(practice.getUserSelect());
                list2.add(multiLook);
            } else {
                pan.add(practice.getQuestionId());
                PanLook panLook = new PanLook();
                panLook.setUserSelect(practice.getUserSelect());
                list3.add(panLook);
            }
        }
        List<Single> singles = singleDAO.findByIds(sin);
        List<Multi> multis = multiDAO.findByIds(mul);
        List<Pan> pans = panDAO.findByIds(pan);
        for(int i = 0; i < singles.size(); i++) {
            SingleLook singleLook = list1.get(i);
            singleLook.setSingle(singles.get(i));
            list1.set(i,singleLook);
        }
        for(int i = 0; i < multis.size(); i++) {
            MultiLook multiLook = list2.get(i);
            multiLook.setMulti(multis.get(i));
            list2.set(i,multiLook);
        }
        for(int i = 0; i < pans.size(); i++) {
            PanLook panLook = list3.get(i);
            panLook.setPan(pans.get(i));
            list3.set(i,panLook);
        }
        map.put("singleLook",list1);
        map.put("multiLook",list2);
        map.put("panLook",list3);
    }
}
