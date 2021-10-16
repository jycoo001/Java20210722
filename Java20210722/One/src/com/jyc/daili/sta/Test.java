package com.jyc.daili.sta;

public class Test {
    public static void main(String[] args) {
        System.out.println("test.main");
        Three three = new Three();
        One one = new Two(three);
        one.show();
    }
}
