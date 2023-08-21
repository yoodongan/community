package com.brad.community.interceptor;

import com.brad.community.service.MemberService;
import com.brad.community.vo.Req;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class BeforeActionInterceptor implements HandlerInterceptor {
    private final MemberService memberService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Req req = new Req(request, response, memberService);
        request.setAttribute("req",req);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
