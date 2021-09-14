package com.jyc.fff.Sql;

public class Oracle implements IDB{
    @Override
    public void getConnection() {
        System.out.println("Oracle.getConnection");
    }
}
