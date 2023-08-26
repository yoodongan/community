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
    private final Req req;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        req.init();
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
