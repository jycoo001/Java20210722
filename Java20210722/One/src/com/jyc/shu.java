package com.jyc;

import java.util.ArrayList;
import java.util.Scanner;

public class shu {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList1 = new ArrayList<>();
        Scanner sa = new Scanner(System.in);
        int i = 0;
        int j = 0;
        String str = sa.nextLine();
        while (str.length() > i) {
            char b = str.charAt(i);
            if (b == ';') {
                i++;
                continue;
            } else if (b == ','){
                i++;
                continue;
            }else {
                int n = b-48;
                arrayList1.add(j, n);
                j++;
                i++;
            }
        }

        int[] li = new int[arrayList1.size()];
        for (i = 0; i < arrayList1.size(); i++) {
            li[i] = arrayList1.get(i);
        }
        for (i = 1; i < li.length; i++) {
            if (li[i] < li[i-1]) {
                int temp = li[i-1];
                li[i-1] = li[i];
                li[i] = temp;
            }
        }
        for (i = 0; i < li.length; i++) {
            System.out.print(li[i]+" ");

        }
        System.out.println();
        System.out.println(li[li.length-4]);
    }
}
