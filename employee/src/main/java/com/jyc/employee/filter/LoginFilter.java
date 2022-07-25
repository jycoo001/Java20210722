package com.jyc.employee.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jyc.employee.model.User;

/**
 * 登陆过滤器
 * 
 * @author 12430
 *
 */
//@WebFilter("/*")
public class LoginFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user_login");

		String path = req.getServletPath();
		if (path.contains("/user") || path.contains("/static") || path.contains("/code")) {
			if (path.equals("/user/list")) {
				resp.sendRedirect(req.getContextPath() + "/user/login");
			} else {
				chain.doFilter(request, response);
			}
		} else {
			if (user == null) {
				resp.sendRedirect(req.getContextPath() + "/user/login");
			} else {
				chain.doFilter(req, resp);
			}
		}
	}
}
