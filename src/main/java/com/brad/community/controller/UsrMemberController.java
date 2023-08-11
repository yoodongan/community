package com.brad.community.controller;

import com.brad.community.service.MemberService;
import com.brad.community.vo.Member;
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
    public Object doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
        Long memberId = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);
        if(memberId == 0L) return "로그인 ID가 중복되었습니다.";
        if(memberId == -1L) return "이름과 email이 중복입니다.";
        Member member = memberService.findById(memberId);
        return member;
    }

}
