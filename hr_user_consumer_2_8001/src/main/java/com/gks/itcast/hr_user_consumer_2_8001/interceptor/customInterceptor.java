package com.gks.itcast.hr_user_consumer_2_8001.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class customInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("respUser");
        if( loginUser != null) {
            //已经登录过,放行
            return true;
        }
        //没有登录过
        session.setAttribute("msg", "没有权限，请先登录！");;
//        request.getRequestDispatcher("/login").forward(request, response);
        response.sendRedirect("/toLogin");
        return false;
    }
}
