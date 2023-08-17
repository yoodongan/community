package com.brad.community.controller;

import com.brad.community.service.MemberService;
import com.brad.community.util.Ut;
import com.brad.community.vo.DataResponse;
import com.brad.community.vo.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/usr/member")
@RequiredArgsConstructor
public class UsrMemberController {
    private final MemberService memberService;

    @RequestMapping("/doJoin")
    @ResponseBody
    public DataResponse doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
        if (Ut.isEmpty(loginId)) return DataResponse.of("F-1", "로그인 id를 입력해주세요.");
        if (Ut.isEmpty(loginPw)) return DataResponse.of("F-1", "비밀번호를 입력해주세요.");
        DataResponse dataResponse = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);
        Member member = memberService.findById((Long) dataResponse.getData()); // Service의 메서드는 가급적 해당 기능만 수행해야 한다. (단일 책임 원칙)
        return DataResponse.ofNew(dataResponse, member);
    }

    @GetMapping("/login")
    public String showLogin() {
        return "/member/login";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public DataResponse doLogin(HttpSession session, String loginId, String loginPw) {
        boolean isLogin = false;
        if (session.getAttribute("loginMemberId") != null) {
            isLogin = true;
        }
        if (isLogin) return DataResponse.of("F-2", "이미 로그인했습니다!");
        if (Ut.isEmpty(loginId)) return DataResponse.of("F-1", "로그인 id를 입력해주세요.");
        if (Ut.isEmpty(loginPw)) return DataResponse.of("F-2", "비밀번호를 입력해주세요.");

        DataResponse dataResponse = memberService.findByLoginId(loginId);
        Member member = (Member) dataResponse.getData();
        if (!member.getLoginPw().equals(loginPw)) return DataResponse.of("F-1", "비밀번호가 일치하지 않습니다.");

        session.setAttribute("loginMemberId", member.getId());
        return DataResponse.of("S-1", Ut.f("%s님 환영합니다.", member.getNickname()));
    }

    @RequestMapping("/doLogout")
    @ResponseBody
    public DataResponse doLogout(HttpSession session) {
        boolean isLogout = false; // 로그아웃 여부
        if (session.getAttribute("loginMemberId") == null) isLogout = true;
        if(isLogout) return DataResponse.of("F-1", "이미 로그아웃 되었습니다.");

        // 로그아웃 처리
        session.removeAttribute("loginMemberId");
        return DataResponse.of("S-1", "로그아웃 되었습니다.");
    }
}
