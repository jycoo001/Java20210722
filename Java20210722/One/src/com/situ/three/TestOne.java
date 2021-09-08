package com.situ.three;

import org.junit.Test;

import java.util.Scanner;

public class TestOne {
    public static void main(String[] args) {
        System.out.println("TestOne.main");
        int num = 2;
        switch (num) {
            case 1:
                ++num;
            case 2:
                ++num;
            case 3:
                ++num;
            default:
                ++num;
                break;
        }
        System.out.println(num);
    }
    @Test
    public void test1 () {
        System.out.println("TestOne.test1");
        String str = "Hello";
        String str1 = "he"+new String("llo");
        System.out.println(str == str1);
    }

    //回文数！
    @Test
    public void test2 () {
        System.out.println("TestOne.test2");
        Scanner sa = new Scanner(System.in);
        char[] a = new char[5];
        System.out.println("请输入五位字符：");
        String str = sa.next();
        boolean b = false;
        for (int i = 0; i < a.length; i++) {
            a[i] = str.charAt(i);
        }
        for (int i = 0; i < a.length; i++) {
            if(a[i] == a[a.length-1-i])
            {
                b = true;
            }else{
                b = false;
                System.out.println(str+"不是回文数!");
                break;
            }
        }
        if (b) {
            System.out.println(str+"是回文数！");
        }

    }

    @Test
    public void test3 () {
        System.out.println("TestOne.test3");
        int[] a = {1,56,23,20,24,55,99,37,69,67,85,30,29};
        for (int i = 0; i < a.length; i++) {
            for (int j = 1; j < a.length; j++) {
                if (a[j] < a[j - 1]) {
                    int temp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = temp;
                    for (int k = 0; k < j; k++) {
                        System.out.print(a[k]+" ");
                    }
                }
            }
        }
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
    }
}
