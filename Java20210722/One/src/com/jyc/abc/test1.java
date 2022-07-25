package com.jyc.abc;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class test1 {
    public static void main(String[] args) {
        Set set = new HashSet();
        while (set.size()<7){
            int num = (int) (Math.random()*1000+1);
            set.add(num);
        }

        Iterator iterator = set.iterator();
        int i=0;
        while (iterator.hasNext()){
            if (i<3){
                if (i==0){
                    System.out.print("三等奖：");
                }
                System.out.print(" "+iterator.next());
            }
            else if (i<5){
                if (i==4){
                    System.out.println();
                    System.out.print("二等奖：");
                }
                System.out.print(" "+iterator.next());
            }else{
                if (i==6){
                    System.out.println();
                    System.out.print("一等奖：");
                }
                System.out.print(" "+iterator.next());
            }
            i++;
        }
    }

}
