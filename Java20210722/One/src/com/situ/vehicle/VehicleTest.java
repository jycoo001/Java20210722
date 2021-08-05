package com.situ.vehicle;

import org.junit.Test;

public class VehicleTest {

    //1、请定义一个交通工具(Vehicle)的类，其中有:
    //属性：速度(speed)，体积(size)等等
    //方法：移动(move())，加速speedUp(int speed),减速speedDown(int speed)等等.
    //最后在测试类VehicleTest中的main()中实例化一个交通工具对象，并通过构造方法给它
    // 初始化speed,size的值，并且通过get方法打印出来。另外，调用加速，减速的方法对速度进行改变（改变speed的值），
    // 改变后的速度值在main()方法中打印出来。
    @Test
    public void test1 () {
        System.out.println("VehicleTest.test1");
        Vehicle ve = new Vehicle(4,5);
        System.out.println("速度："+ve.getSpeed()+" 体积"+ve.getSize());
        ve.speedUp(ve.getSpeed());
        System.out.println("加速后速度："+ve.getSpeed());
        ve.speedDown(ve.getSpeed());
        System.out.println("减速后速度："+ve.getSpeed());
        System.out.println("请输入移动时间：");
        int move = ve.move();
        System.out.println("移动距离："+move);
    }
}
