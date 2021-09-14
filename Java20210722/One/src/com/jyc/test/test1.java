package com.jyc.test;

import org.junit.Test;

public class test1 {
    @Test
    public void test1() {
        System.out.println("test1.test1");
        int[] a = {13,67,34,99,34,56,109,55,68};
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (a[i]<a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
                System.out.print(a[j]+" ");
            }
            System.out.println();
        }
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
