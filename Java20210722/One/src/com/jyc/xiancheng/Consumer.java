package com.jyc.xiancheng;

import java.util.Random;

public class Consumer extends Thread{
    private Panzi panzi;
    public Consumer(Panzi panzi) {
        this.panzi = panzi;
    }
    @Override
    public void run() {
        for (int i = 1; i < 1000; i++) {
            Cake cake = panzi.getCake();
            System.out.println("消费者getCake : " + cake);
            try {
                Thread.sleep(new Random().nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
