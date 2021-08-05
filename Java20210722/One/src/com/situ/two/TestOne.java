package com.situ.two;

import org.junit.Test;

import java.util.Scanner;

public class TestOne {
    //学生的查找
    @Test
    public void test1() {
        System.out.println("TestOne.test1");
        Scanner sca = new Scanner(System.in);
        System.out.println("请输入学生数量:");
        int count = sca.nextInt();
        Student[] student = new Student[count];
        for (int i = 0; i < student.length; i++) {
            System.out.println("请输入id号");
            int id = sca.nextInt();
            System.out.println("请输入姓名");
            String name = sca.next();
            System.out.println("请输入年龄");
            int age = sca.nextInt();
            Student student1 = new Student(id,name,age);
            student[i] = student1;
        }

        while (true) {
            System.out.println("1、通过id号查找学生");
            System.out.println("2、通过姓名查找学生");
            System.out.println("3、通过年龄查找学生");
            System.out.println("请输入要选择的功能");
            int a = sca.nextInt();
            switch (a) {
                case 1:
                    System.out.println("请输入id号");
                    int b = sca.nextInt();
                    boolean b1 = false;
                    for (int i = 0; i < student.length; i++) {
                        if (b==student[i].getId())  {
                            b1 = true;
                            System.out.println("查找成功！");
                            System.out.println("id号："
                                    +student[i].getId() +" 姓名："
                                    +student[i].getName()+" 年龄："
                                    +student[i].getAge());
                        }
                    }
                    if (!b1) {
                        System.out.println("查找失败！请重新输入！");
                    }
                    break;
                case 2:
                    System.out.println("请输入姓名");
                    String c = sca.next();
                    boolean b2 = false;
                    for (int i = 0; i < student.length; i++) {
                        if (c.equals(student[i].getName()))  {
                            System.out.println("查找成功！");
                            b2 = true;
                            System.out.println("id号："
                                    +student[i].getId()+" 姓名："
                                    +student[i].getName()+" 年龄："
                                    +student[i].getAge());
                        }
                    }
                    if (!b2) {
                        System.out.println("查找失败！请重新输入！");
                    }
                    break;
                case 3:
                    System.out.println("请输入年龄" );
                    int d = sca.nextInt();
                    boolean b3 = false;
                    for (int i = 0; i < student.length; i++) {
                        if (d==student[i].getAge())  {
                            System.out.println("查找成功");
                            b3 = true;
                            System.out.println("id号："
                                    +student[i].getId()+" 姓名："
                                    +student[i].getName()+" 年龄："
                                    +student[i].getAge());
                        }
                    }
                    if (!b3) {
                        System.out.println("查找失败！请重新输入！");
                    }
                    break;
                default:
                    System.out.println("输入错误！请重新输入");
                    break;
            }
        }


    }
}
