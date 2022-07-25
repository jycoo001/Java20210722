package com.jyc.sdau.interceptor;

import com.jyc.sdau.model.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("####user_login####");
        if (user != null) {
            return true;
        } else {
            response.sendRedirect(request.getContextPath() + "/forward/login");
            return false;
        }
    }
}
