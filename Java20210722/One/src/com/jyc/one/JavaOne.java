package com.jyc.one;

import java.util.Scanner;

public class JavaOne {
    public static void main(String[] args) {
        Scanner sa = new Scanner(System.in);
        int a = sa.nextInt();
        int b = sa.nextInt();
        System.out.println("a="+a+" b="+b);
        int c = 0;
        c = a;
        a = b;
        b = c;
        System.out.println("a="+a+" b="+b);
    }

}
