package com.situ.fff;

import com.situ.two.Student;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FanShe {

    //反射
    @Test
    public void test1() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //class.forname 建立连接, 得到”字节码对象“
        Class clazz = Class.forName("com.situ.two.Student");
        //constructor 构造方法 getConstructor() 无参构造方法
        Constructor con =  clazz.getConstructor();
        //调用一个构造方法实例化一个对象
        Student student = (Student) con.newInstance();
        //通过反射拿到setName方法
        Method method = clazz.getMethod("setName", String.class);
        //通过反射调用setName方法，设置对象student的name为张三
        method.invoke(student, "张三");

        Method method1 = clazz.getMethod("getName");
        String str1 = (String) method1.invoke(student);
        System.out.println(str1);
    }

    @Test
    public void test2() throws ClassNotFoundException {
        System.out.println("FanShe.test2");
        //拿到字节码对象的三种方式 都是同一个对象 唯一的
        //1、class.forname 常用的
        Class clazz1 = Class.forName("com.situ.two.Student");
        //2、*。class
        Class clazz2 = Student.class;
        //3、new 一个对象之后在getClass
        Student student = new Student();
        Class clazz3 = student.getClass();
    }

}
