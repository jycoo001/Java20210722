package com.jyc;

import java.util.ArrayList;
import java.util.Scanner;

public class shangPin {
    public static void main(String[] args) {
        Scanner sa = new Scanner(System.in);
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList = Sca(sa);
        int a = sa.nextInt();
        int[] li = lis(arrayList);
        int n = 0;
        for (int i = li.length-1; i > 0 ; i--) {
            if (a>li[i]) {
                if (a % li[i] == 0) {
                    n++;
                    break;
                } else {
                    a = a % li[i];
                    n++;
                }
            }else{
                continue;
            }
        }
    }

    public static ArrayList<Integer> Sca(Scanner sa) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        String str = ",";
        int n = sa.nextInt();
        arrayList.add(n);
        while (str.equals(sa.nextLine())) {
            arrayList.add(sa.nextInt());
        }
        return arrayList;
    }

    public static int[] lis(ArrayList<Integer> arrayList) {
        int[] li = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            li[i] = arrayList.get(i);
        }
        for (int i = 1; i < li.length; i++) {
            if (li[i] < li[i-1]) {
                int temp = li[i-1];
                li[i-1] = li[i];
                li[i] = temp;
            }
        }
        return li;
    }
}
