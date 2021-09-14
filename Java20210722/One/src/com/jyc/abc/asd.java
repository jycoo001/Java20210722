package com.jyc.abc;

public class asd extends Thread {
    public static void main(String[] args) {
        try {
            System.out.println("asd.main");
            sleep(100000);
            System.out.println("end...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
