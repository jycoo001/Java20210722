package com.situ.timeTest;


import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeDemo {

    @Test
    public void test1 () {
        Date date = new Date();
        System.out.println(date);
        SimpleDateFormat sdate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = sdate.format(date);
        System.out.println(time);
    }
}
