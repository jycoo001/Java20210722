package com.jyc.forward.service.impl;

import com.jyc.forward.dao.*;
import com.jyc.forward.model.*;
import com.jyc.forward.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TestServiceImpl implements TestService {
    private MultiDAO multiDAO;
    private SingleDAO singleDAO;
    private PanDAO panDAO;
    private PracticeDAO practiceDAO;
    private UserPracticeDAO userPracticeDAO;
    @Autowired
    public void setSingleDAO(SingleDAO singleDAO) {
        this.singleDAO = singleDAO;
    }
    @Autowired
    public void setMultiDAO(MultiDAO multiDAO) {
        this.multiDAO = multiDAO;
    }
    @Autowired
    public void setPanDAO(PanDAO panDAO) {
        this.panDAO = panDAO;
    }
    @Autowired
    public void setPracticeDAO(PracticeDAO practiceDAO) {
        this.practiceDAO = practiceDAO;
    }
    @Autowired
    public void setUserPracticeDAO(UserPracticeDAO userPracticeDAO) {
        this.userPracticeDAO = userPracticeDAO;
    }
    @Override
    public void sel(Question question,Map<String,Object> map,List<Integer> listSingle,List<Integer> listMu,List<Integer> listPan) {
        //业务
        //单选
        if(question.getSingle()!=null && question.getSingle()>0) {
            int sinCount = singleDAO.findCount();
            int sn = question.getSingle();
            if(sn>=sinCount) {
                sn = sinCount;
                for(int i =1; i <= sn; i++) {
                    listSingle.add(i);
                }
            } else {
                List<Integer> list = new ArrayList<>();
                for(int i = 1; i <= sinCount; i++) {
                    list.add(i);
                }
                for(int i = 0; i < sn; i++) {
                    int index = (int)(Math.random()*(list.size()-1)+1);
                    int value = list.get(index);
                    list.remove(index);
                    listSingle.add(value);
                }
            }
            List<Single> list = singleDAO.findByIds(listSingle);
            map.put("single",list);
        }
        //多选
        if(question.getMulti()!=null && question.getMulti()>0) {
            int muCount = multiDAO.findCount();
            int mu = question.getMulti();
            if(mu>=muCount) {
                mu = muCount;
                for(int i = 0; i <= mu; i++) {
                    listMu.add(i);
                }
            } else {
                List<Integer> list = new ArrayList<>();
                for(int i = 1; i <= muCount; i++) {
                    list.add(i);
                }
                for(int i = 0; i < mu; i++) {
                    int index = (int)(Math.random()*(list.size()-1)+1);
                    int value = list.get(index);
                    list.remove(index);
                    listMu.add(value);
                }
            }
            List<Multi> list = multiDAO.findByIds(listMu);
            map.put("multi",list);
        }
        //判断
        if(question.getPan()!=null && question.getPan()>0) {
            int paCount = panDAO.findCount();
            int pa = question.getPan();
            if(pa>=paCount) {
                pa = paCount;
                for(int i = 1; i <= paCount; i++) {
                    listPan.add(i);
                }
            } else {
                List<Integer> list = new ArrayList<>();
                for(int i = 1; i <= paCount; i++) {
                    list.add(i);
                }
                for(int i = 0; i < pa; i++) {
                    int index = (int)(Math.random()*(list.size()-1)+1);
                    int value = list.get(index);
                    list.remove(index);
                    listPan.add(value);
                }
            }
            List<Pan> list = panDAO.findByIds(listPan);
            map.put("pan",list);
        }


    }

    @Override
    public int save(String single, String multi, String pan, List<Integer> listSin, List<Integer> listMu, List<Integer> listPa, Integer userId, String uuid) {
        double group = 100.0/(listSin.size()+listMu.size()*2+listPa.size()/2.0);
        UserPractice userPractice = new UserPractice();
        Date time = Calendar.getInstance().getTime();
        double allGroup = manageSingle(single,listSin,uuid,time,group)+manageMulti(multi,listMu,uuid,time,group)+managePan(pan,listPa,uuid,time,group);
        userPractice.setUserId(userId);
        userPractice.setUuid(uuid);
        userPractice.setTime(time);
        userPractice.setGroup(allGroup);
        int row = userPracticeDAO.insert(userPractice);
        return row;
    }

    /**
     * 单选处理
     * @param single
     * @param listSin
     * @param uuid
     * @param time
     * @param group
     * @return
     */
    public double manageSingle(String single,List<Integer> listSin,String uuid,Date time,double group) {
        List<Single> list = singleDAO.findByIds(listSin);
        String manage[] = single.split(",");
        List<Practice> listP = new ArrayList<>();
        double all = 0.0;
        for(int i = 0; i < manage.length; i++) {
            Practice practice = new Practice();
            practice.setType("单选");
            practice.setTime(time);
            practice.setUuid(uuid);
            Integer questionId = listSin.get(i);
            practice.setQuestionId(questionId);
            String right = list.get(i).getRight();
            if(manage[i].trim().equals(right)) {
                practice.setRight(right);
                practice.setUserSelect(manage[i]);
                practice.setGroup(group);
                all+=group;
            } else {
                practice.setRight(right);
                practice.setUserSelect(manage[i]);
                practice.setGroup(0.0);
                all+=0.0;
            }
            practiceDAO.insert(practice);
        }
        return all;
    }

    /**
     * 多选处理
     * @param multi
     * @param listMu
     * @param uuid
     * @param time
     * @param group
     * @return
     */
    public double manageMulti(String multi,List<Integer> listMu,String uuid,Date time,double group) {
        List<Multi> list = multiDAO.findByIds(listMu);
        String manage[] = multi.split(";");
        List<Practice> listP = new ArrayList<>();
        double all = 0.0;
        for(int i = 0; i < manage.length; i++) {
            Practice practice = new Practice();
            practice.setType("多选");
            practice.setTime(time);
            practice.setUuid(uuid);
            Integer questionId = listMu.get(i);
            practice.setQuestionId(questionId);
            String right = list.get(i).getRight();
            if(manage[i].trim().length()>0) {
                manage[i] = manage[i].substring(0,manage[i].length()-1);
            }
            if(manage[i].trim().equals(right)) {
                practice.setRight(right);
                practice.setUserSelect(manage[i]);
                practice.setGroup(group*2);
                all+=group*2;
            } else {
                practice.setRight(right);
                practice.setUserSelect(manage[i]);
                practice.setGroup(0.0);
                all+=0.0;
            }
            practiceDAO.insert(practice);
        }
        return all;
    }

    /**
     * 判断处理
     * @param pan
     * @param listPa
     * @param uuid
     * @param time
     * @param group
     * @return
     */
    public double managePan(String pan,List<Integer> listPa,String uuid,Date time,double group) {
        List<Pan> list = panDAO.findByIds(listPa);
        String manage[] = pan.split(",");
        List<Practice> listP = new ArrayList<>();
        double all = 0.0;
        for(int i = 0; i < manage.length; i++) {
            Practice practice = new Practice();
            practice.setType("判断");
            practice.setTime(time);
            practice.setUuid(uuid);
            Integer questionId = listPa.get(i);
            practice.setQuestionId(questionId);
            String right = list.get(i).getRight();
            if(manage[i].trim().equals(right)) {
                practice.setRight(right);
                practice.setUserSelect(manage[i]);
                practice.setGroup(group*0.5);
                all+=group*0.5;
            } else {
                practice.setRight(right);
                practice.setUserSelect(manage[i]);
                practice.setGroup(0.0);
                all+=0.0;
            }
            practiceDAO.insert(practice);
        }
        return all;
    }


}
