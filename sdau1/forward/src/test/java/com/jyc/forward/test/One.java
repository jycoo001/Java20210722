package com.jyc.forward.test;

import org.junit.jupiter.api.Test;

import java.util.*;

public class One {
    @Test
    public void test() {
        List<Integer> list = new ArrayList<>();
        int count = 100;
        for(int i = 1; i <= 100; i++) {
            list.add(i);
        }
        int sn = 10;
        List<Integer> list1 = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            int index = (int)(Math.random()*(list.size()-1)+1);
            int value = list.get(index);
            list1.add(value);
            list.remove(index);
        }
        for(int i = 0; i < sn; i++) {
            System.out.print(list1.get(i)+" ");
        }
    }

    @Test
    public void test2() {
        String str = "a,b, ,c,d,";
        String token[] = str.split(",");
        System.out.println(token.length);
        for(int i = 0; i < token.length; i++) {
            System.out.println(token[i]);
        }
    }

    @Test
    public void test3() {
        String str = "a,b,;c,d,; ;";
        String token[] = str.split(";");
        System.out.println(token.length);
        for(int i = 0; i < token.length; i++) {
            System.out.println(token[i]);
        }
    }

    @Test
    public void test4() {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < 15; i++) {
            set.add(i);
        }
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next()+" ");
        }

    }

    @Test
    public void test5() {
        double group = 100.0/(10+6+0.5);
        double all = (10+6+0.5)*group;
        double dd = 6.06*(10+6+0.5);
        System.out.println(group);
        System.out.println(all+" "+dd);
    }
}
