package Test1;

import org.junit.Test;

import java.util.Random;


public class Test1 {
@Test
	public void Test() {
	//定义随机数
	Random random = new Random();
	//定义数组
	int array[] = new int[10];
	for(int i:array)
	{
		array[i] = random.nextInt(10);
		//循环输出放入数组的随机数
		System.out.print(array[i]);
	}
	//定义最大值
	int max = array[0];
	//取最大值
	for(int i=1;i<array.length;i++)
		if(array[i]>array[i-1])
		{
			max = array[i];
		}
	System.out.println();
	System.out.print(max);
	//倒序输出
	for(int i=array.length-1;i>=0;i--)
	{
		array[i] = random.nextInt(10);
		System.out.print(array[i]);
	}
	System.out.println();
	for(int i:array)
		System.out.print(array[i]);
	//判断==5
	int flag = 0;
	for(int i:array)
		if(array[i]==5) {
			flag=1;
			break;}
	System.out.println();
	System.out.println(flag==1?"存在":"不存在");
}
}
