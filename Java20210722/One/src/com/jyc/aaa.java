package com.jyc;

import java.util.Scanner;

public class aaa {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String S = scanner.next();
        String T = scanner.next();
        int k = 0;
        int l = 0;
        for (int i = 0; i < S.length(); i++) {
            k = 0;
            for (int j = 0; j < T.length(); j++) {
                if (S.charAt(i) == T.charAt(j)) {
                    continue;
                }else {
                    k++;
                }
                if (k == T.length()-1) {

                }
            }

        }
    }
}
