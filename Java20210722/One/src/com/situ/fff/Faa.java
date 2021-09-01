package com.situ.fff;

public class Faa {
    public static void main(String[] args) {
        /*int result = fa(6);
        System.out.println(result);
        int res = feibo(7);
        System.out.println(res);*/
        int[] a = {1,5,45,55,66,76,78,99,100,103,110};
        int low = 0;
        int hight = a.length - 1;
        erfen(a, low, 78, hight);
    }

    //n的阶乘
    public static int fa(int a) {
        if (a == 1) {
            return 1;
        }
        return a * fa(a-1);
    }

    //斐波那契数列
    public static int feibo(int n) {
        if (n <= 1) {
            return n;
        }
        return feibo(n - 1) + feibo(n - 2);
    }

    //二分查找
    public static void erfen(int[] a, int low, int target, int hight) {
        int mid = (low + hight) / 2;
        if (a[mid] == target) {
            System.out.println("二分查找 找到了! mid:" + mid + " low : " + low + " high : " + hight + " target : " + target);
        }
        if (a[mid] > target) {
            hight = mid - 1;
            erfen(a, low, target, hight);
        }
        if (a[mid] < target) {
            low = mid + 1;
            erfen(a, low, target, hight);
        }
    }
}
