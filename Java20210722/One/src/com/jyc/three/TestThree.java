package com.jyc.three;

import org.junit.Test;

import java.util.Scanner;

public class TestThree {
    @Test
    public void test1 () {
        Scanner st = new Scanner(System.in);
        String str = st.nextLine();
        String str1 = trim(str);
        System.out.println(str1);
    }
    public String trim (String str) {
        try {
            int start = 0;
            int end = str.length() - 1;
            while ((str.charAt(start) == '-') && (start <= end)) {
                start++;
            }
            while ((start <= end) && (str.charAt(end) == '-')) {
                end--;
            }
            return str.substring(start, end+1);
        }catch (Exception e) {
            return e.getMessage();
        }

    }
}
