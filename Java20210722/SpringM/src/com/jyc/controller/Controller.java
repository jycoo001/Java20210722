package com.jyc.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.stereotype.Controller
@RequestMapping("/lo")
public class Controller {

    @RequestMapping("/lo")
    @ResponseBody
    public String aa(Model model) {
        return "aaaaaa";
    }
}
