package com.situ.three;

import org.junit.Test;

import java.util.Scanner;

public class TestFive {
    //单词计数
    @Test
    public void test1 () {
        Scanner sc = new Scanner(System.in);
        System.out.println("TestFive.test1");
        System.out.println("请输入单词：");
        String str = sc.nextLine();
        char[] st = str.toCharArray();
        int count = 1;
        for (int i = 0; i < st.length; i++) {
            if (st[i] == ' ') {
                count++;
            }
        }
        System.out.println("共有"+count+"个单词");
    }
}
