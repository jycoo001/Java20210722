package com.jyc.rectangle;

import org.junit.Test;

import java.util.Scanner;

public class RectangleTest {
    @Test
    public void test1 () {
        Scanner r = new Scanner(System.in);
        System.out.println("RectangleTest.test1");
        System.out.println("请输入长方形宽高：");
        System.out.println("宽：");
        int n1 = r.nextInt();
        System.out.println("高：");
        int n2 = r.nextInt();
        Rectangle re = new Rectangle(n1,n2);
        System.out.println("周长："+re.Girth()+"面积："+re.Area());
    }
}
