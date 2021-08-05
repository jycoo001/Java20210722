package com.situ.three;

import org.junit.Test;

import java.util.Scanner;

public class TestThree {
    @Test
    public void test1 () {
        Scanner st = new Scanner(System.in);
        String str = st.next();
        str.trim();
        System.out.println(str);
    }
}
