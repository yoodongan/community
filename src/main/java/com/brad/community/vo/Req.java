package com.brad.community.vo;

import com.brad.community.service.MemberService;
import com.brad.community.util.Ut;
import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
public class Req {
    private Long loginMemberId;
    private boolean isLogin;
    private HttpServletRequest request;
    private HttpServletResponse response;

    private Member member;
    private HttpSession session;
    public Req(HttpServletRequest request, HttpServletResponse response, MemberService memberService) {
        this.request = request;
        this.response = response;
        loginMemberId = 0L;
        isLogin = false;
        member = null;
        this.session = request.getSession();  // 세션 객체를 가져와야 NPE가 발생하지 않는다.

        if(session.getAttribute("loginMemberId") != null) {
            this.isLogin = true;
            this.loginMemberId = (Long) session.getAttribute("loginMemberId");
            this.member = memberService.findById(loginMemberId);
        }

        this.request.setAttribute("req", this);   // 여기서 request에 req 객체를 넣어준다.
    }
    /* 로그인, 로그아웃을 통한 세션 관리를 Req 클래스에서 관리한다. */
    public void login(Member member) {
        session.setAttribute("loginMemberId", member.getId());
    }
    public void logout() {
        session.removeAttribute("loginMemberId");
    }


    public void printHistoryBack(String msg) {
        response.setContentType("text/html; charset=UTF-8");
        println("<script>");
        if(!Ut.isEmpty(msg)) print("alert('" + msg +"');");
        print("history.back();");
        println("</script>");
    }
    public void print(String str) {
        try {
            response.getWriter().append(str);
        } catch (IOException e) {
        }
    }
    public void println(String str) {
        print(str + "\n");
    }

    /* 프록시 객체인 Req 를 초기화하는 메서드이다. */
    public void init() {
    }
}