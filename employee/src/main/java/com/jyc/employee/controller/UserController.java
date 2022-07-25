package com.jyc.employee.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jyc.employee.model.User;
import com.jyc.employee.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping(value = { "/login", "/register", "/back", "/update" })
	public String toL(HttpSession session, HttpServletRequest req, Map<String, Object> map) {
		map.put("detail", session.getAttribute("detail"));
		session.removeAttribute("detail");
		System.out.println("user" + req.getServletPath().replace("/user", ""));
		return "user" + req.getServletPath().replace("/user", "");
	}

	@PostMapping("/login")
	public String login(HttpSession session, Map<String, Object> map, User user, String code) {
		String sessionCode = "" + session.getAttribute("verification");
		if (code.equals(sessionCode)) {
			session.removeAttribute("verification");
			User user1 = userService.login(user);
			if (user1 != null) {
				session.setAttribute("detail", "登录成功！");
				session.setAttribute("user_login", user1);
				session.setMaxInactiveInterval(10 * 60);
				return "redirect:/index";
			} else {
				map.put("detail", "登陆失败！用户名或密码错误！");
				map.put("user", user);
				return "user/login";
			}
		} else {
			map.put("detail", "登陆失败！验证码有误！");
			map.put("user", user);
			return "user/login";
		}
	}

	@PostMapping("/register")
	public String register(HttpSession session, Map<String, Object> map, User user) {
		int rows = userService.register(user);
		if (rows > 0) {
			session.setAttribute("detail", "注册成功！");
			return "redirect:/user/login";
		} else {
			map.put("detail", "注册失败");
			map.put("user", user);
			return "user/register";
		}
	}

	@PostMapping("/back")
	public String back(HttpSession session, Map<String, Object> map, User user) {
		if (user.getPassword() == null || user.getPassword().trim().equals("")) {
			User us = userService.login(user);
			map.put("user", us);
			return "user/back";
		} else {
			int rows = userService.update(user);
			if (rows > 0) {
				session.setAttribute("detail", "修改成功！");
				return "redirect:/user/login";
			} else {
				map.put("detail", "修改失败");
				map.put("user", user);
				return "user/back";
			}
		}
	}

	@PostMapping("/update")
	public String update(HttpSession session, Map<String, Object> map, User user) {
		if (user.getPassword() != null && !user.getPassword().trim().equals("")) {
			user.setPassword("");
		}
		int rows = userService.update(user);
		if (rows > 0) {
			session.setAttribute("detail", "修改成功！");
			return "redirect:/user/list";
		} else {
			map.put("detail", "修改失败");
			map.put("user", user);
			return "user/update";
		}
	}

	@GetMapping("/list")
	public String list(User user, Map<String, Object> map) {
		List<User> list = userService.querySelector(user);
		map.put("userList", list);
		map.put("user", user);
		return "user/list";
	}

	@GetMapping("/outlogin")
	public String logout(HttpSession session) {
		session.removeAttribute("user_login");
		return "redirect:/index";
	}

}
