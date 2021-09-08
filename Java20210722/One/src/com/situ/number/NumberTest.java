package com.situ.number;

import org.junit.Test;

import java.util.Scanner;

public class NumberTest {
    @Test
    public void test1 () {
        System.out.println("NumberTest.test1");
        System.out.println("请输入数字，进行四则运算：");
        Scanner nu = new Scanner(System.in);
        System.out.print("num1 = ");
        int num1 = nu.nextInt();
        System.out.println();
        System.out.print("num2 = ");
        int num2 = nu.nextInt();
        System.out.println();
        Number num = new Number(num1,num2);
        System.out.println("加："+num.add());
        System.out.println("减："+num.sub());
        System.out.println("乘："+num.multi());
        System.out.println("除："+num.div());
    }
}
