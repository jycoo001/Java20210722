package com.jyc.filter;

import com.jyc.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登陆过滤器
 * 
 * @author 12430
 *
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}

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
