package com.brad.community.controller;

import com.brad.community.service.MemberService;
import com.brad.community.util.Ut;
import com.brad.community.vo.DataResponse;
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
    public DataResponse doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
        if(Ut.isEmpty(loginId)) return DataResponse.of("F-1", "로그인 id를 입력해주세요.");
        if(Ut.isEmpty(loginPw)) return DataResponse.of("F-1", "비밀번호를 입력해주세요.");
        DataResponse dataResponse = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);
        Member member = memberService.findById((Long) dataResponse.getData()); // Service의 메서드는 가급적 해당 기능만 수행해야 한다. (단일 책임 원칙)
        return DataResponse.ofNew(dataResponse, member);
    }

}
