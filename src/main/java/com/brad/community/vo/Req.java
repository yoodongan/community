package com.brad.community.vo;

import lombok.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Data
public class Req {
    private Long loginMemberId;
    private boolean isLogin;
    public Req(HttpServletRequest req) {
        loginMemberId = 0L;
        isLogin = false;
        HttpSession session = req.getSession();
        if(session.getAttribute("loginMemberId") != null) {
            isLogin = true;
            loginMemberId = (Long) session.getAttribute("loginMemberId");
        }
    }
}