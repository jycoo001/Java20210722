package com.situ.xiancheng;

public class Test2 implements Runnable{
    @Override
    public void run() {
        System.out.println("Test2.run");
        for (int i = 0; i < 10000; i++) {
            System.out.print(Thread.currentThread().getName() + " : " + i);
        }
    }
}
