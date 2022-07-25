package com.jyc.forward.service.impl;

import com.jyc.forward.dao.*;
import com.jyc.forward.model.*;
import com.jyc.forward.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ExamServiceImpl implements ExamService {
    private UserExamDAO dao;
    @Autowired
    public void setDao(UserExamDAO dao) {
        this.dao = dao;
    }
    private ExamDAO examDAO;
    @Autowired
    public void setExamDAO(ExamDAO examDAO) {
        this.examDAO = examDAO;
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

    /**
     * 查看未考的
     * @param userId
     * @return
     */
    @Override
    public List<UserExam> find(Integer userId) {
        List<UserExam> list = dao.findByUserId(userId);
        List<UserExam> list1 = new ArrayList<>();
        Date time = Calendar.getInstance().getTime();
        for(int i = 0; i < list.size(); i++) {
            UserExam userExam = list.get(i);
            Date start = userExam.getStartTime();
            Date last = userExam.getLastTime();
            String status = userExam.getIsDoing();
            if(time.compareTo(start)>=0 && time.compareTo(last)<0 && status.equals("未做")) {
                list1.add(list.get(i));
            }
        }
        return list1;
    }

    @Override
    public void toDoing(String uuid, Map<String,Object> map, HttpSession session) {
        List<Exam> list = examDAO.findByUUId(uuid);
        List<Integer> single = new ArrayList<>();
        List<Integer> multi = new ArrayList<>();
        List<Integer> pan = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            Exam exam = list.get(i);
            String type = exam.getType();
            if("单选".equals(type)) {
                single.add(exam.getQuestionId());
            } else if("多选".equals(type)) {
                multi.add(exam.getQuestionId());
            } else {
                pan.add(exam.getQuestionId());
            }
        }
        List<Single> singles = singleDAO.findByIds(single);
        List<Multi> multis = multiDAO.findByIds(multi);
        List<Pan> pans = panDAO.findByIds(pan);
        map.put("single",singles);
        map.put("multi",multis);
        map.put("pan",pans);
        session.setAttribute("singleId",single);
        session.setAttribute("multiId",multi);
        session.setAttribute("panId",pan);
        session.setAttribute("uuid",uuid);
    }

    @Override
    public int save(String single, String multi, String pan, List<Integer> listSin, List<Integer> listMu, List<Integer> listPa, Integer userId, String uuid) {
        double group = 100.0/(listSin.size()+listMu.size()*2+listPa.size()/2.0);
        Date time = Calendar.getInstance().getTime();
        double allGroup = manageSingle(single,listSin,uuid,time,group)+manageMulti(multi,listMu,uuid,time,group)+managePan(pan,listPa,uuid,time,group);
        UserExam userExam = dao.findByUUId(uuid);
        userExam.setGroup(allGroup);
        userExam.setIsDoing("已做");
        int row = dao.update(userExam);
        return row;
    }

    @Override
    public List<UserExam> findHis(Integer userId) {
        List<UserExam> list = dao.findByUserId(userId);
        List<UserExam> list1 = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            UserExam userExam = list.get(i);
            String status = userExam.getIsDoing();
            if(status.equals("已做")) {
                list1.add(list.get(i));
            }
        }
        return list1;
    }

    @Override
    public void findHisExam(String uuid,Map<String,Object> map) {
        UserExam userExam = dao.findByUUId(uuid);
        List<Exam> list = userExam.getList();
        List<SingleLook> list1 = new ArrayList<>();
        List<MultiLook> list2 = new ArrayList<>();
        List<PanLook> list3 = new ArrayList<>();
        List<Integer> sin = new ArrayList<>();
        List<Integer> mul = new ArrayList<>();
        List<Integer> pan = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            Exam exam = list.get(i);
            String type = exam.getType();
            if(type.equals("单选")) {
                sin.add(exam.getQuestionId());
                SingleLook singleLook = new SingleLook();
                singleLook.setUserSelect(exam.getUserSelect());
                list1.add(singleLook);
            } else if(type.equals("多选")) {
                mul.add(exam.getQuestionId());
                MultiLook multiLook = new MultiLook();
                multiLook.setUserSelect(exam.getUserSelect());
                list2.add(multiLook);
            } else {
                pan.add(exam.getQuestionId());
                PanLook panLook = new PanLook();
                panLook.setUserSelect(exam.getUserSelect());
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
        String manage[] = single.split(",");
        double all = 0.0;
        int row = 0;
        for(int i = 0; i < manage.length; i++) {
            Exam exam = new Exam();
            exam.setUuid(uuid);
            Integer questionId = listSin.get(i);
            exam.setQuestionId(questionId);
            exam.setType("单选");
            List<Exam> exams = examDAO.findAll(exam);
            Exam exam1 = exams.get(0);
            String right = exam1.getRight();
            if(manage[i].trim().equals(right)) {
                exam1.setTime(time);
                exam1.setUserSelect(manage[i]);
                exam1.setGroup(group);
                all+=group;
            } else {
                exam1.setTime(time);
                exam1.setUserSelect(manage[i]);
                exam1.setGroup(0.0);
                all+=0.0;
            }
            row += examDAO.update(exam1);
        }
        System.out.println("单选更新了："+row);
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
        String manage[] = multi.split(";");
        double all = 0.0;
        int row = 0;
        for(int i = 0; i < manage.length; i++) {
            Exam exam = new Exam();
            exam.setType("多选");
            exam.setUuid(uuid);
            Integer questionId = listMu.get(i);
            exam.setQuestionId(questionId);
            List<Exam> list = examDAO.findAll(exam);
            Exam exam1 = list.get(0);
            String right = exam1.getRight();
            if(manage[i].trim().length()>0) {
                manage[i] = manage[i].substring(0,manage[i].length()-1);
            }
            if(manage[i].trim().equals(right)) {
                exam1.setUserSelect(manage[i]);
                exam1.setTime(time);
                exam1.setGroup(group*2);
                all+=group*2;
            } else {
                exam1.setTime(time);
                exam1.setUserSelect(manage[i]);
                exam1.setGroup(0.0);
                all+=0.0;
            }
            row += examDAO.update(exam1);
        }
        System.out.println("多选修改了："+row);
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
        String manage[] = pan.split(",");
        int row = 0;
        double all = 0.0;
        for(int i = 0; i < manage.length; i++) {
            Exam exam = new Exam();
            exam.setType("判断");
            exam.setUuid(uuid);
            Integer questionId = listPa.get(i);
            exam.setQuestionId(questionId);
            List<Exam> list = examDAO.findAll(exam);
            Exam exam1 = list.get(0);
            String right = list.get(i).getRight();
            if(manage[i].trim().equals(right)) {
                exam1.setTime(time);
                exam1.setUserSelect(manage[i]);
                exam1.setGroup(group*0.5);
                all+=group*0.5;
            } else {
                exam1.setTime(time);
                exam1.setRight(right);
                exam1.setUserSelect(manage[i]);
                exam1.setGroup(0.0);
                all+=0.0;
            }
            row += examDAO.update(exam1);
        }
        System.out.println("判断修改了："+row);
        return all;
    }


}
