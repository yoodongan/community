package com.brad.community.interceptor;

import com.brad.community.vo.Req;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class BeforeLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("실행되었나 ?");
        Req req = (Req) request.getAttribute("req");
        if(!req.isLogin()) {
            req.printHistoryBack("로그인 후 이용해주세요.");
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
