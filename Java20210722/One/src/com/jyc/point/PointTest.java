package com.jyc.point;

import org.junit.Test;

import java.util.Scanner;

public class PointTest {
    @Test
    public void test1 () {
        System.out.println("PointTest.test1");
        Scanner sp = new Scanner(System.in);
        System.out.println("请输入x坐标:");
        int x = sp.nextInt();
        System.out.println("请输入y坐标：");
        int y = sp.nextInt();
        Point p1 = new Point(x,y);
        System.out.println("请输入dx：");
        int x1 = sp.nextInt();
        System.out.println("请输入dy：");
        int y1 = sp.nextInt();
        p1.movePoint(x1,y1);
    }
}
