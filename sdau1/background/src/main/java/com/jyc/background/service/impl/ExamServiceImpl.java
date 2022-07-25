package com.jyc.background.service.impl;

import com.jyc.background.dao.*;
import com.jyc.background.model.*;
import com.jyc.background.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ExamServiceImpl implements ExamService {
    private PanDAO panDAO;
    private MultiDAO multiDAO;
    private ExamDAO examDAO;
    private SingleDAO singleDAO;
    private UserDAO userDAO;
    private UserExamDAO dao;

    @Autowired
    public void setDao(UserExamDAO dao) {
        this.dao = dao;
    }
    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setSingleDAO(SingleDAO singleDAO) {
        this.singleDAO = singleDAO;
    }

    @Autowired
    public void setExamDAO(ExamDAO examDAO) {
        this.examDAO = examDAO;
    }

    @Autowired
    public void setMultiDAO(MultiDAO multiDAO) {
        this.multiDAO = multiDAO;
    }

    @Autowired
    public void setPanDAO(PanDAO panDAO) {
        this.panDAO = panDAO;
    }

    @Override
    public List<UserExam> findAll(UserExam userExam) {
        return dao.findAll(userExam);
    }

    @Override
    public UserExam findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public UserExam findByUUId(String uuid) {
        return dao.findByUUId(uuid);
    }

    @Override
    public List<UserExam> findByUserExam(String str) {
        return dao.findByUserExam(str);
    }

    @Override
    public List<UserExam> findByUserId(Integer userId) {
        return dao.findByUserId(userId);
    }

    @Override
    public int insert(ExamSet examSet) {
        Integer single = examSet.getSingle();
        Integer multi = examSet.getMulti();
        Integer pan = examSet.getPan();
        Integer startId = examSet.getStartId();
        Integer lastId = examSet.getLastId();
        Date startTime = examSet.getStartTime();
        Date lastTime = examSet.getLastTime();
        int userCount = userDAO.findCount();
        if(startId<=0) {
            startId=1;
        }
        if(startId>userCount || startId>lastId) {
            return 0;
        }
        if(lastId>=userCount) {
            lastId=userCount;
        }
        int row = 0;
        for(int i = startId; i <= lastId;i++) {
            String uuid = UUID.randomUUID().toString();
            UserExam userExam = new UserExam();
            userExam.setUuid(uuid);
            userExam.setStartTime(startTime);
            userExam.setLastTime(lastTime);
            userExam.setUserId(i);
            userExam.setGroup(0.0);
            userExam.setIsDoing("未做");
            row += manage(single,uuid,"单选")+manage(multi,uuid,"多选")+manage(pan,uuid,"判断");
            row+=dao.insert(userExam);
        }
        return row;
    }

    private int manage(Integer single, String uuid, String type) {
        List<Integer> listSingle = new ArrayList<>();
        int sinCount;
        if(type.equals("单选")) {
            sinCount = singleDAO.findCount();
        } else if(type.equals("多选")) {
            sinCount = multiDAO.findCount();
        } else {
            sinCount = panDAO.findCount();
        }
        if(single>=sinCount) {
            single = sinCount;
            for(int i =1; i <= single; i++) {
                listSingle.add(i);
            }
        } else {
            List<Integer> list = new ArrayList<>();
            for(int i = 1; i <= sinCount; i++) {
                list.add(i);
            }
            for(int i = 0; i < single; i++) {
                int index = (int)(Math.random()*(list.size()-1)+1);
                int value = list.get(index);
                list.remove(index);
                listSingle.add(value);
            }
        }
        int row = 0;
        if(type.equals("单选")) {
            List<Single> list = singleDAO.findByIds(listSingle);
            for(int i = 0; i < list.size(); i++) {
                Single single1 = list.get(i);
                Integer questionId = single1.getId();
                String right = single1.getRight();
                Exam exam = new Exam();
                exam.setUuid(uuid);
                exam.setRight(right);
                exam.setType(type);
                exam.setGroup(0.0);
                exam.setQuestionId(questionId);
                row += examDAO.insert(exam);
            }
        } else if(type.equals("多选")) {
            List<Multi> list = multiDAO.findByIds(listSingle);
            for(int i = 0; i < list.size(); i++) {
                Multi multi1 = list.get(i);
                Integer questionId = multi1.getId();
                String right = multi1.getRight();
                Exam exam = new Exam();
                exam.setUuid(uuid);
                exam.setRight(right);
                exam.setType(type);
                exam.setGroup(0.0);
                exam.setQuestionId(questionId);
                row+=examDAO.insert(exam);
            }
        } else {
            List<Single> list = singleDAO.findByIds(listSingle);
            for(int i = 0; i < list.size(); i++) {
                Single single1 = list.get(i);
                Integer questionId = single1.getId();
                String right = single1.getRight();
                Exam exam = new Exam();
                exam.setUuid(uuid);
                exam.setRight(right);
                exam.setType(type);
                exam.setGroup(0.0);
                exam.setQuestionId(questionId);
                row+=examDAO.insert(exam);
            }
        }
        return  row;
    }

    @Override
    public int update(UserExam userExam) {
        return dao.update(userExam);
    }

    @Override
    public int delete(Integer id) {
        int row = 0;
        UserExam userExam = dao.findById(id);
        String uuid = userExam.getUuid();
        List<Exam> list = examDAO.findByUUId(uuid);
        List<Integer> list1 = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            list1.add(list.get(i).getId());
        }
        row += examDAO.deleteMany(list1);
        return row+dao.delete(id);
    }

    @Override
    public int deleteMany(Integer[] ids) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < ids.length; i++) {
            list.add(ids[i]);
        }
        List<UserExam> list1 = dao.findByIds(list);
        List<Integer> list3 = new ArrayList<>();
        for(int i = 0; i < list1.size(); i++) {
            String uuid = list1.get(i).getUuid();
            List<Exam> list2 = examDAO.findByUUId(uuid);
            for(int j = 0; j< list2.size(); j++) {
                list3.add(list2.get(j).getId());
            }
        }
        int row = examDAO.deleteMany(list3);
        return row + dao.deleteMany(list);
    }

    @Override
    public List<UserExam> findByIds(Integer[] ids) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < ids.length; i++) {
            list.add(ids[i]);
        }
        return dao.findByIds(list);
    }
}
