package com.situ.one;

import java.util.Scanner;

public class JavaTwo {
    public static void main(String[] args) {
        Scanner sa = new Scanner(System.in);
        double a = sa.nextDouble();
        int b = sa.nextInt();
        double c = sa.nextDouble();
        double d = a*b;
        if(d>=500)
            d = d*0.8;
        if(c-d>0){
            System.out.println("应收金额："+d);
            System.out.println("找回零钱："+(c-d));
        }else{
            System.out.println("金额不足！");
            System.out.println("请再交："+(d-c)+"元");
        }
    }
}
