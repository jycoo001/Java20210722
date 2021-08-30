package com.situ.xiancheng;

import java.util.Random;

public class Producter extends Thread{
    private Panzi panzi;
    public Producter(Panzi panzi) {
        this.panzi = panzi;
    }
    @Override
    public void run() {
        for (int i = 1; i < 1000; i++) {
            Cake cake = new Cake("no: " + i);
            panzi.putCake(cake);
            System.out.println("生产者 putCake : " + cake);
            try {
                Thread.sleep(new Random().nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
