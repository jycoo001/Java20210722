package com.jyc.sdau.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/background")
public class BackgroundIndex {
    @RequestMapping("/index")
    public String index() {
        return "background/index";
    }

    @RequestMapping("/home")
    public String home() {
        return "background/home";
    }
}
