package com.jyc.fff.Sql;

public class SqlServer implements IDB{
    @Override
    public void getConnection() {
        System.out.println("SqlServer.getConnection");
    }
}
