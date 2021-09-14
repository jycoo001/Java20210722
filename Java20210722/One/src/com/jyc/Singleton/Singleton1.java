package com.jyc.Singleton;

//懒汉式
public class Singleton1 {
    //volatile 共享内存 一个线程改了，另一个能查到
    private volatile static Singleton1 instance;

    private Singleton1() {

    }

    //线程不安全，加锁一
    /*public synchronized static Singleton1 getInstance() {
        if (instance==null) {
            instance = new Singleton1();
        }
        return instance;
    }*/
    //加锁二
    public static Singleton1 getInstance() {
        //双重检查
        if (instance==null) {
            synchronized (Singleton1.class) {
                if (instance == null) {
                    instance = new Singleton1();
                }
            }
        }
        return instance;
    }
}
