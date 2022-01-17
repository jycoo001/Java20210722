package com.jyc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    public void hello() {
        System.out.println("HelloController.hello");
        System.out.println("hello world!");
    }
}
