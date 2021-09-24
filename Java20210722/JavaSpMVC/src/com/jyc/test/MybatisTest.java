package com.jyc.test;

import com.jyc.util.MybatisUtil;
import com.jyc.vo.BanJiStudent;
import com.jyc.vo.StudentBanJi;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisTest {

    @Test
    public void test1() throws IOException {
        System.out.println("MybatisTest.test1");
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        //selectAll
        int pageNo = 1;
        int pageSize = 5;
        int offset = (pageNo - 1) * pageSize;
        Map<String, Object> map = new HashMap<>();
        map.put("offset", offset);
        map.put("pageSize", pageSize);
        map.put("sname", "贾");
        List<StudentBanJi> list = sqlSession.selectList("student.selectLike", map);
        for (StudentBanJi studentBanJi : list) {
            System.out.println(studentBanJi);
        }
        //selectById
        /*Student student = sqlSession.selectOne("student.selectById", 2);
        System.out.println(student);*/

        //insert+selectAll
        /*Student student = new Student("defxsc", "男", 15, 1);
        int result = sqlSession.insert("student.insert", student);
        System.out.println(result);
        sqlSession.commit();
        List<StudentBanJi> list = sqlSession.selectList("student.selectAll");
        for (StudentBanJi studentBanJi : list) {
            System.out.println(studentBanJi);
        }*/

        //deleteById
        /*int result = sqlSession.delete("student.deleteById", 45);
        System.out.println(result);
        sqlSession.commit();*/

        //update
        /*Student student = new Student(36, "大床房", "男", 21, 3);
        int result =  sqlSession.update("student.update", student);
        System.out.println(result);
        sqlSession.commit();*/
    }

    @Test
    public void test2() throws IOException {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        List<BanJiStudent> banJiStudents = sqlSession.selectList("banji.selectBanjiStudent");
        for (BanJiStudent banJi : banJiStudents) {
            //System.out.println(banJi.getId()+" "+banJi.getName());
            System.out.println(banJi);
            /*List<Student> list = banJi.getList();
            for (Student student : list) {
                System.out.println(student);
            }*/
        }
    }
}
