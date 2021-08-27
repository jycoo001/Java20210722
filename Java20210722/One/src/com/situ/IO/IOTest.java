package com.situ.IO;

import com.situ.two.Student;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;

public class IOTest {

    /*
    * 字符流
    * 字节流
    * obj对象流 序列化，反序列化
    * */
    //单个读
    @Test
    public void test1() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("io.txt");
            int a = fileReader.read();
            System.out.println(a);
            System.out.println((char)a);
            int b = fileReader.read();
            System.out.println(b);
            System.out.println((char)b);
            int c = fileReader.read();
            System.out.println(c);
            System.out.println((char)c);
            int d = fileReader.read();
            System.out.println(d);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //循环读
    @Test
    public void test2() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("./io.txt");//io.txt
            int ch = -1;
            while ((ch = fileReader.read()) != -1) {
                System.out.print(ch+"-");
                System.out.println((char)ch);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //读多个
    @Test
    public void test3() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("./io.txt");//io.txt
            int length = -1;
            char[] buffer = new char[5];
            while ((length = fileReader.read(buffer)) != -1) {
                System.out.println(Arrays.toString(buffer));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //写
    @Test
    public void test4() {
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            fileReader = new FileReader("./io.txt");//io.txt
            fileWriter = new FileWriter("./io_w.txt");
            int length = -1;
            char[] buffer = new char[5];
            while ((length = fileReader.read(buffer)) != -1) {
                System.out.println(Arrays.toString(buffer));
                /*[1, 2, 3, 4, 5]
                  [6, 7, 8, 9, 0]
                  [a, b, 8, 9, 0]*/
                //writer(buffer)1234567890ab890
                //fileWriter.write(buffer);
                fileWriter.write(buffer, 0, length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //byte字节流复制 图片1.jpg复制到2.jpg
    @Test
    public void testInOut() {
        System.out.println("IOTest.testInOut");
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream("./1.jpg");
            fileOutputStream = new FileOutputStream("./2.jpg");
            int length = -1;
            byte[] bytes = new byte[1024];
            while ((length = fileInputStream.read(bytes)) !=-1) {
                fileOutputStream.write(bytes, 0, length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //对象流obj 序列化
    @Test
    public void testObj() {
        Student student = new Student(1, "张三", 21);
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {

            fileOutputStream = new FileOutputStream("student");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(student);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //反序列化 从student读取对象
    @Test
    public void textObjIn() {
        ObjectInputStream objectInputStream = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("student");
            objectInputStream = new ObjectInputStream(fileInputStream);
            Student student = (Student) objectInputStream.readObject();
            System.out.println(student);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
