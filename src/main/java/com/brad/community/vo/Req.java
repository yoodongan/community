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
    public Req(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        loginMemberId = 0L;
        isLogin = false;
        HttpSession session = request.getSession();
        if(session.getAttribute("loginMemberId") != null) {
            isLogin = true;
            loginMemberId = (Long) session.getAttribute("loginMemberId");
        }
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