package com.situ.xiancheng;

public class Main {
    public static void main(String[] args) {
        System.out.println("Main.main");
        Panzi panzi = new Panzi();
        //生产者
        Producter producter = new Producter(panzi);
        producter.start();
        //消费者
        Consumer consumer = new Consumer(panzi);
        consumer.start();

    }
}
