package com.jyc.fff.Sql;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBFactory {
    //简单工厂

    public static IDB creatDB() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        FileInputStream fileInputStream = new FileInputStream("src/com/situ/fff/Sql/db.properties");
        //FileInputStream fileInputStream = new FileInputStream("../Java20210722/One/src/com/situ/fff/Sql/db.properties");
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
        return db;
    }

    public static IDB creatDB1(String name) {
        IDB db = null;
        switch (name) {
            case "MySql":
                db = new MySql();
                break;
            case "Oracle":
                db = new Oracle();
                break;
            case "SqlServer":
                db = new SqlServer();
                break;
        }
        return db;
    }
}
