package com.jyc.abc;

public class mao {
    public static void main(String[] args) {
        int[] num = {12,15,46,56,87,23,4,36};
        for(int i = 0; i < num.length; i++) {
            for (int j = 0; j < num.length-i-1; j++) {
                if(num[j]>num[j+1]) {
                    int tmp = num[j];
                    num[j] = num[j+1];
                    num[j+1] = tmp;
                }
            }
        }
    }
}
