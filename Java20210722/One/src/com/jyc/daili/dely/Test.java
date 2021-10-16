package com.jyc.daili.dely;

public class Test {
    public static void main(String[] args) {
        System.out.println("Test.main");
        One two = new Two();
        One one = (One) ProxyFactory.getObjectInstance(two);
        one.show();

        BanjiService banjiService = new BanjiServiceImpl();
        BanjiService banjiService1 = (BanjiService) ProxyFactory.getObjectInstance(banjiService);
        banjiService1.selectOne();
    }
}
