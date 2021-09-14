package com.jyc.xiancheng;

public class ThreadDemo {
    public static void main(String[] args) {
        Test1 test1 = new Test1();
        //test1.run();
        test1.start();
        Test2 test2 = new Test2();
        Thread thread = new Thread(test2);
        thread.start();

    }
}
