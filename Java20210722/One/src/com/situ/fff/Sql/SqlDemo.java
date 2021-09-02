package com.situ.fff.Sql;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class SqlDemo {

    //JUnit不可以 FileInputStream fileInputStream = new FileInputStream("src/com/situ/fff/Sql/db.properties");
    //出错junit
    //main可以 FileInputStream fileInputStream = new FileInputStream("../Java20210722/One/src/com/situ/fff/Sql/db.properties");
    // FileInputStream fileInputStream = new FileInputStream("D:\\idea_project\\Java20210722\\One\\src\\com\\situ\\fff\\Sql\\db.properties");
    // Junit可以FileInputStream fileInputStream = new FileInputStream("../Java20210722/One/src/com/situ/fff/Sql/db.properties");

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        System.out.println("SqlDemo.main");
        //MySql mySql = new MySql();
        //mySql.getConnection();
        /*IDB db = new MySql();
        db.getConnection();*/
        //FileInputStream fileInputStream = new FileInputStream("src/com/situ/fff/Sql/db.properties");
        FileInputStream fileInputStream = new FileInputStream("../Java20210722/One/src/com/situ/fff/Sql/db.properties");
        //FileInputStream fileInputStream = new FileInputStream("D:\\idea_project\\Java20210722\\One\\src\\com\\situ\\fff\\Sql\\db.properties");
        Properties properties = new Properties();
        properties.load(fileInputStream);
        String className = properties.getProperty("className");
        System.out.println(className);

        /*Class clazz = Class.forName(className);
        Constructor constructor = clazz.getConstructor();
        IDB db = (IDB) constructor.newInstance();
        db.getConnection();*/
        Class clazz = Class.forName(className);
        IDB db = (IDB) clazz.newInstance();
        db.getConnection();
    }

    @Test
    public void test1 () {
        System.out.println("SqlDemo.test1");
        IDB db = DBFactory.creatDB1("MySql");
        db.getConnection();
    }

    @Test
    public void test2() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        System.out.println("SqlDemo.test2");
        IDB db = DBFactory.creatDB();
        db.getConnection();
    }
}
