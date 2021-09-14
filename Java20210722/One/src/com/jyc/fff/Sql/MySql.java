package com.jyc.fff.Sql;

public class MySql implements IDB{
    @Override
    public void getConnection() {
        System.out.println("MySql.getConnection");
    }
}
