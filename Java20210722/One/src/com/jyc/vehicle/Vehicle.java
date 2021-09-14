package com.jyc.vehicle;

import java.util.Scanner;

//1、请定义一个交通工具(Vehicle)的类，其中有:
//属性：速度(speed)，体积(size)等等
//方法：移动(move())，加速speedUp(int speed),减速speedDown(int speed)等等.
//最后在测试类VehicleTest中的main()中实例化一个交通工具对象，并通过
// 构造方法给它初始化speed,size的值，并且通过get方法打印出来。另外，调用
// 加速，减速的方法对速度进行改变（改变speed的值），改变后的速度值在main()方法中打印出来。
public class Vehicle {
    private int speed;
    private int size;

    public Vehicle() {
        System.out.println("Vehicle.Vehicle");
    }

    public Vehicle(int speed, int size) {
        this.speed = speed;
        this.size = size;
    }

    public int getSpeed() {
        return speed;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "speed=" + speed +
                ", size=" + size +
                '}';
    }

    public int move () {
        int time = 0;
        Scanner timeInput = new Scanner(System.in);
        time = timeInput.nextInt();
        return time*speed;
    }
    public void speedUp (int speed) {
        this.speed = ++speed;
    }
    public void speedDown (int speed) {
        this.speed = --speed;
    }
}
