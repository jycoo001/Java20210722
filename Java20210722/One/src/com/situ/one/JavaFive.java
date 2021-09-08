package com.situ.one;

import org.junit.Test;

import java.util.Scanner;

public class JavaFive {

    @Test
    public void test1() {
        for (int i=0;i<5;i++)   {
            for (int j=0;j<5;j++)   {
                if(i<5-j)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }

    @Test
    public void test2() {
        Scanner x = new Scanner(System.in);
        System.out.println("请输入a:");
        int a = x.nextInt();
        System.out.println("请输入n");
        int n = x.nextInt();
        int mu = 1;
        int num = 0;
        for(int i = 0;i<n;i++)  {
            mu = a*mu;
            num = mu+num;
        }
        System.out.println(num);
    }

    @Test
    public void test3() {
        int[] a = {80,45,60,100,89,92,93,78,56,90,97,85,88,74,75,79,99,69};
        int n1 = 0;
        int n2 = 0;
        int n3 = 0;
        for (int i = 0;i < a.length;i++)  {
            if(a[i] == 100) {
                n1++;
            }else if (a[i] >= 90 && a[i] <= 99) {
                n2++;
            }else if(a[i] >= 80 && a[i] <= 89)  {
                n3++;
            }
        }
        System.out.println("100分人数"+n1);
        System.out.println("90-99分人数"+n2);
        System.out.println("80-89分人数"+n3);
    }
}
