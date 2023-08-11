package com.brad.community.service;

import com.brad.community.repository.MemberRepository;
import com.brad.community.vo.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    private boolean isMemberDuplicated(String loginId) {
        Member member = memberRepository.findByLoginId(loginId);
        if(member != null) return true; // 중복되었다.
        return false;
    }
    public Long join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
        // 로그인 아이디로 회원 찾아와서, 존재한다면 회원가입이 진행되면 안됨.
        boolean flag = isMemberDuplicated(loginId);
        if(flag) return 0L;  // 만약 로그인 아이디가 중복이라면, 0을 리턴한다.
        memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);
        return memberRepository.getLastInsertId();
    }

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
