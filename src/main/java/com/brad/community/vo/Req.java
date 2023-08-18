package com.brad.community.vo;

import com.brad.community.util.Ut;
import lombok.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Data
public class Req {
    private Long loginMemberId;
    private boolean isLogin;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    public Req(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        loginMemberId = 0L;
        isLogin = false;
        this.session = request.getSession();  // 세션 객체를 가져와야 NPE가 발생하지 않는다.
        if(session.getAttribute("loginMemberId") != null) {
            isLogin = true;
            loginMemberId = (Long) session.getAttribute("loginMemberId");
        }
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

}