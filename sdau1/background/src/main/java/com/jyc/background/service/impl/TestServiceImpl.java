package com.jyc.background.service.impl;

import com.jyc.background.dao.PracticeDAO;
import com.jyc.background.dao.UserPracticeDAO;
import com.jyc.background.model.Practice;
import com.jyc.background.model.Single;
import com.jyc.background.model.UserPractice;
import com.jyc.background.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TestServiceImpl implements TestService {
    private UserPracticeDAO dao;
    private PracticeDAO practiceDAO;
    @Autowired
    public void setDao(UserPracticeDAO dao) {
        this.dao = dao;
    }

    @Autowired
    public void setPracticeDAO(PracticeDAO practiceDAO) {
        this.practiceDAO = practiceDAO;
    }

    @Override
    public List<UserPractice> findAll(UserPractice userPractice) {
        return dao.findAll(userPractice);
    }

    @Override
    public UserPractice findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public UserPractice findByUUId(String uuid) {
        return dao.findByUUId(uuid);
    }

    @Override
    public List<UserPractice> findByUserPractice(String str) {
        return dao.findByUserPractice(str);
    }

    @Override
    public List<UserPractice> findByUserId(Integer userId) {
        return dao.findByUserId(userId);
    }

    @Override
    public int insert(UserPractice userPractice) {
        return dao.insert(userPractice);
    }

    @Override
    public int update(UserPractice userPractice) {
        return dao.update(userPractice);
    }

    @Override
    public int delete(Integer id) {
        UserPractice userPractice = dao.findById(id);
        List<Practice> list = userPractice.getPractice();
        int rows = 0;
        List<Integer> list1 = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            list1.add(list.get(i).getId());
        }
        rows+=practiceDAO.deleteMany(list1);
        rows+=dao.delete(id);
        return rows;
    }

    @Override
    public int deleteMany(Integer[] ids) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < ids.length; i++) {
            list.add(ids[i]);
        }
        Set<Integer> set = new HashSet<>();
        List<UserPractice> userPracticeList = dao.findByIds(list);
        for(int i = 0; i < userPracticeList.size();i++) {
            UserPractice userPractice = userPracticeList.get(i);
            List<Practice> practiceList = userPractice.getPractice();
            for(int j = 0; j < practiceList.size(); j++) {
                Practice practice = practiceList.get(j);
                set.add(practice.getId());
            }
        }
        List<Integer> list1 = new ArrayList<>();
        Iterator<Integer> ite = set.iterator();
        while (ite.hasNext()) {
            list1.add(ite.next());
        }
        int rows = practiceDAO.deleteMany(list1);
        rows+=dao.deleteMany(list);
        return rows;
    }
}
