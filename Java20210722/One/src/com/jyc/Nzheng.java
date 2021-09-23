package com.jyc;

import java.util.Scanner;

public class Nzheng {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        String str2 = scanner.nextLine();
        int n = str1.charAt(0) - 48;
        int m = str1.charAt(2) - 48;
        int[] a = new int[n];
        int ma = 0;//和数
        int count = 0;
        for (int i = 0; i < a.length; i+=2) {
            a[i] = str2.charAt(i) - 48;
        }

        for (int i = 0; i < a.length; i++) {
            ma = a[i];
            for (int j = i+1; j < a.length; j++) {
                ma = ma + a[j];
                for (int o = j+1; o <a.length; o++) {
                    ma = ma + a[o];
                    if (ma % m == 0) {
                        count++;
                    } else {
                            ma = ma - a[o];
                    }
                }
                ma = ma - a[j];
            }
            ma = ma - a[i];
        }
        System.out.println(count);
    }
}
