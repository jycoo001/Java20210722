package com.jyc.one;

import org.junit.Test;

import java.util.Scanner;

public class JavaThree {
    @Test
    public void test1() {
        System.out.println(" ");
    }
    @Test
    public void test2() {
        System.out.println("请输入月份：");
        Scanner sa = new Scanner(System.in);
        int a = sa.nextInt();
        switch (a) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                System.out.println(a+"月31天");
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                System.out.println(a+"月30天");
                break;
            case 2:
                System.out.println(a+"月29或28天");
                break;
            default:
                System.out.println("输入错误！");
                break;
        }

    }

    @Test
    public void test3() {
        for(int i=0;i<3;i++){
            switch(i){
                case 1:
                    System.out.println("a");
                    break;
                case 0:
                    System.out.println("b");
                    break;
                default:
                    System.out.println("c");
                case 2:
                    System.out.println("d");
            }
        }


    }

}
