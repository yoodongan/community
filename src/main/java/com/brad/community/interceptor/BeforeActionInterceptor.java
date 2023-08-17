package com.brad.community.interceptor;

import com.brad.community.vo.Req;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class BeforeActionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Req req = new Req(request);
        request.setAttribute("req",req);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
