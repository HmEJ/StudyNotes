package com.handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*自定义拦截器*/
public class MyHandler implements HandlerInterceptor {
    /* preHandle 发送请求到达处理器之前
    * 返回值如果为false, 则表示该请求被拦截。如果为true，则表示放行
    * */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        /*在控制器方法执行前*/
        System.out.println("pre1");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("post1");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        /*视图渲染之后执行*/
        System.out.println("after1");
    }
}
