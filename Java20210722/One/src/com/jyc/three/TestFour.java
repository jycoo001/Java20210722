package com.jyc.three;

import org.junit.Test;

import java.util.Scanner;

public class TestFour {
    //字符全部转换为小写
    @Test
    public void test1 () {
        System.out.println("TestFour.test1");
        Scanner st = new Scanner(System.in);
        System.out.println("请输入字符串：");
        String str = st.nextLine();
        String str1 = toLowerCase(str);
        System.out.println(str1);
    }

    //把大写字符变为小写
    public String toLowerCase (String str) {
        char[] st = str.toCharArray();
        for (int i = 0;i < st.length; i++) {
            if (st[i] >= 'A' && st[i] <= 'Z') {
                st[i] = (char)(st[i]+32);
            }
        }
        String str1 = String.valueOf(st);
        return str1;
    }

    //把小写字符变为大写
    public String toUpperCase (String str) {
        char[] st = str.toCharArray();
        for (int i = 0;i < st.length; i++) {
            if (st[i] >= 'a' && st[i] <= 'z') {
                st[i] = (char)(st[i]-32);
            }
        }
        String str1 = String.valueOf(st);
        return str1;
    }

}
