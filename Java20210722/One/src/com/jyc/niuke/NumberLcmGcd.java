package com.jyc.niuke;

import java.util.*;

/**
 *  度度熊请你找出两个数 a,b 满足1<=a,b<=n,且lcm(a,b)-gcd(a,b)最大,输出最大的 lcm表示a和b的最小公倍数
 *  gcd表示a和b的最大公约数
 * @author 12430
 */
public class NumberLcmGcd {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long a = Calendar.getInstance().getTimeInMillis();
        int max = 0;
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= i; j++) {
                int x = lcm(i, j);
                if (x > max) {
                    max = x;
                }
            }
        }

        System.out.println(max);
        long b = Calendar.getInstance().getTimeInMillis();
        System.out.println((b - a) + "毫秒");
    }

    /**
     * 返回最小公倍数
     *
     * @param a
     * @param b
     * @return
     */
    public static int lcm(int a, int b) {
        if(a==b) {
            return 0;
        }
        if(a%b==0) {
            return a-b;
        }
        if(b%a==0) {
            return b-a;
        }
        int gcd = gcd(a, b);
        return (a * b) / gcd - gcd;
    }

    /**
     * 返回最大公约数 = 3,6:3 4,5:1
     *
     * @param a
     * @param b
     * @return
     */
    public static int gcd(int a, int b) {
        return a%b==0?b:gcd(b,a%b);
    }
}
