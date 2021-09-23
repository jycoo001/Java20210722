package com.jyc.test;

import com.jyc.util.MybatisUtil;
import com.jyc.vo.StudentBanJi;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class MybatisTest {

    @Test
    public void test1() throws IOException {
        System.out.println("MybatisTest.test1");
        SqlSession sqlSession = MybatisUtil.MybatisXml();
        //selectAll
        List<StudentBanJi> list = sqlSession.selectList("student.selectAll");
        for (StudentBanJi studentBanJi : list) {
            System.out.println(studentBanJi);
        }
        //selectById
       /* Student student = sqlSession.selectOne("student.selectById", 2);
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
}
