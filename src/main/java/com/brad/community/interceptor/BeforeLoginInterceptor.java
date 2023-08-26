package com.brad.community.interceptor;

import com.brad.community.vo.Req;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class BeforeLoginInterceptor implements HandlerInterceptor {
    private final Req req;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!req.isLogin()) {
            req.printHistoryBack("로그인 후 이용해주세요.");
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
