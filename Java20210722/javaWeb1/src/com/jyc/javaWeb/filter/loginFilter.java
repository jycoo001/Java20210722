package com.jyc.javaWeb.filter;

import com.jyc.javaWeb.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "login", urlPatterns = "/*")
public class loginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("loginFilter.init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("loginFilter.doFilter");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String servletPath = httpServletRequest.getServletPath();
        String method = httpServletRequest.getParameter("method");



        if (servletPath.equals("/login.jsp")
                || (servletPath.equals("/user")&&method.equals("login"))
                || servletPath.equals("/fail.jsp")
                || servletPath.equals("/code")
                || servletPath.endsWith(".css")
                || servletPath.endsWith(".js")) {
            chain.doFilter(request, response);
            return;
        }
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.jsp");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("loginFilter.destroy");
    }
}
