package com.jyc.xiancheng;

import java.util.LinkedList;

public class Panzi {

    private LinkedList<Cake> list = new LinkedList<>();

    public synchronized void putCake(Cake cake) {
        list.addLast(cake);
        notifyAll();
        System.out.println("生产者生产!");
    }

    public synchronized Cake getCake() {
        if (list.size() <= 0) {
            try {
                wait();
                System.out.println("消费者等待！-------------");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Cake cake = list.removeFirst();
        return cake;
    }
}
