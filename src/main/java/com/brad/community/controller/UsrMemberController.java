package com.brad.community.controller;

import com.brad.community.service.MemberService;
import com.brad.community.util.Ut;
import com.brad.community.vo.DataResponse;
import com.brad.community.vo.Member;
import com.brad.community.vo.Req;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/usr/member")
@RequiredArgsConstructor
@Slf4j
public class UsrMemberController {
    private final MemberService memberService;
    private final Req req;

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
    public String doLogin(String loginId, String loginPw) {
        boolean isLogin = false;
        if(req.getLoginMemberId() != null) {
            isLogin = true;
        }

        if (isLogin) Ut.historyBack("이미 로그인했습니다!");
        if (Ut.isEmpty(loginId)) Ut.historyBack("로그인 id를 입력해주세요.");
        if (Ut.isEmpty(loginPw)) Ut.historyBack("비밀번호를 입력해주세요.");

        DataResponse dataResponse = memberService.findByLoginId(loginId);
        Member member = (Member) dataResponse.getData();
        if (!member.getLoginPw().equals(loginPw)) Ut.historyBack("비밀번호가 일치하지 않습니다.");

        req.login(member);  // 이제, 여기서 session.setAttribute()를 진행한다.
        return Ut.replace(Ut.f("%s님 환영합니", member.getNickname()), "/");
    }

    @RequestMapping("/doLogout")
    public String doLogout() {
        boolean isLogout = false; // 로그아웃 여부
        if (req.getLoginMemberId() == null) isLogout = true;
        if(isLogout) Ut.historyBack("이미 로그아웃 되었습니다.");
        // 로그아웃 처리
        req.logout();
        Ut.historyBack("로그아웃 되었습니다!");
        return "redirect:/usr/main";
    }
}
