package com.jyc.interceptor;


/*
<mvc:interceptor>
    <mvc:mapping path="/user/**"/>
    <bean class="com.hao.config.LoginInterceptor"/>
</mvc:interceptor>
*/

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OneInterceptor implements HandlerInterceptor {


    /**
     * 在调用 Controller 之前调用
     * @param request
     * @param response
     * @param o
     * @return true 表示继续流程(调用下一个拦截器或处理器) false 表示中断流程, 不在调用其它拦截器或处理器, 需要通过 response 来响应。
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
       return true;
    }


    /**
     * 在Controller调用之后, DispatcherServlet返回渲染视图之前被调用, 可操作ModelAndView对象对试图进行渲染操作。
     * 注意: ModelAndView对象有可能为null
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在视图渲染完毕后调用
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
