package com.situ.xiancheng;

public class Test1 extends Thread{


    @Override
    public void run() {
        System.out.println("Test1.run");
        for (int i = 0; i < 10000; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
        }
    }
}
