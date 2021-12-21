package com.jyc.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/index")
	public String index(HttpSession session, Map<String, Object> map) {
		map.put("detail", session.getAttribute("detail"));
		session.removeAttribute("detail");
		map.put("user", session.getAttribute("user_login"));
		return "index";
	}
}
