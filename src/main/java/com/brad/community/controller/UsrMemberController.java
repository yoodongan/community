package com.brad.community.controller;

import com.brad.community.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/usr/member")
@RequiredArgsConstructor
public class UsrMemberController {
    private final MemberService memberService;

    @RequestMapping("/doJoin")
    @ResponseBody
    public String doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
        memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);
        return "성공";
    }

}
