package com.hmall.common.interceptors;

import cn.hutool.core.util.StrUtil;
import com.hmall.common.utils.UserContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname UserInfoInterceptor
 * @Description
 * @Date 2023/12/16 下午3:56
 * @Created by joneelmo
 */
public class UserInfoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在控制器执行之前获取用户信息
        //1.获取登陆的用户信息
        String userInfo = request.getHeader("user-info");
        //2.判断是否获取了用户，如果有存入ThreadLocal
        if (StrUtil.isNotBlank(userInfo)){
            UserContext.setUser(Long.valueOf(userInfo));
        }
        //3.放行（我们springmvc中拦截器不做登陆校验，请求一律放行）
        return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清理用户信息
        UserContext.removeUser();
    }
}
