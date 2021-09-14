package com.jyc.one;

import org.junit.Test;

public class JavaFour {
    //九九乘法表
    @Test
    public void test1() {
        for (int i=0;i<10;i++)  {
            for (int j=0;j<=i;j++)   {
                System.out.print(" "+i+"*"+j+"="+i*j+" ");
            }
            System.out.println();
        }
    }
//    1、输出一下结构：
//    1
//    12
//    123
//    1234
//    12345
    @Test
    public void test2() {
        for (int i = 1;i < 6;i++)   {
            for (int j = 1;j <= i;j++)  {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    //2、打印正三角形和倒三角形
    @Test
    public void test3() {
        for (int i = 0;i < 6;i++)   {
            for (int j = 0;j < 11;j++)  {
                if(j >= 5-i && j <= 5+i)
                System.out.print("*");
                else System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        for (int i = 0;i < 6;i++)   {
            for (int j = 0;j < 11;j++)  {
                if (j >= i && j <=10-i)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }

    //3、计算1-100以内所有奇数的和以及所有偶数的和，分别打印出来。
    @Test
    public void test4() {
        int a = 0;
        int b = 0;
        for(int i=1;i<=100;i++)
        {
            if(i%2==0) {
                a = i+a;
            }else   {
                b = i+b;
            }
        }
        System.out.println("奇数之和为："+b);
        System.out.println("偶数之和为："+a);
    }

    //4、用for循环输出1—1000之间能被5整除的数，且每行输出3个
    @Test
    public void test5() {
        int j=0;
        for (int i = 1;i <= 1000;i++)   {
            if(i%5==0)  {
                System.out.print(i+" ");
                j++;
                if(j%3==0)  {
                    System.out.println();
                    j=0;
                }
            }
        }
    }

    //5、计算9的阶乘
    @Test
    public void test6() {
        int num = 1;
        for (int i=9;i>=1;i--)  {
            num = i*num;
        }
        System.out.println(num);
    }

    //6、使用循环打印100-200之间所有的素数（只能被1和自己整除的数叫素数）
    @Test
    public void test7() {
        int j=2;
        for (int i = 100;i <= 200;i++)  {
            j=2;
            while(j!=i) {
                if(i%j==0)  {
                    break;
                }else   {
                    j++;
                }
                if(j==i-1){
                    System.out.print(i+" ");
                }
            }
        }
    }
}

