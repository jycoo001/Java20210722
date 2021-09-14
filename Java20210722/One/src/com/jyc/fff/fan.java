package com.jyc.fff;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class fan {
    @Test
    public void test3() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = Student.class;
        Constructor constructor = clazz.getDeclaredConstructor(String.class);

        constructor.setAccessible(true);

        Student student = (Student) constructor.newInstance("王五");
        System.out.println(student);

    }
}
