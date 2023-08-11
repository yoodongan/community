package com.brad.community.service;

import com.brad.community.repository.MemberRepository;
import com.brad.community.vo.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    private boolean isLoginIdDuplicated(String loginId) {
        Member member = memberRepository.findByLoginId(loginId);
        if(member != null) return true; // 중복되었다.
        return false;
    }
    private boolean isDuplicatedWithNameAndMail(String name, String email) {
        Member member = memberRepository.findByNameAndEmail(name, email);
        if(member != null) return true; // 중복.
        return false;
    }
    public Long join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
        // 로그인 아이디로 회원 찾아와서, 존재한다면 회원가입이 진행되면 안됨.
        boolean loginIdDup = isLoginIdDuplicated(loginId); // Dup은 Duplicated의 줄임
        boolean nameAndEmailDup = isDuplicatedWithNameAndMail(name, email);
        if(loginIdDup) return 0L;  // 만약 로그인 아이디가 중복이라면, 0을 리턴한다.
        if(nameAndEmailDup) return -1L; // 만약 name,email 중복이라면 -1을 리턴한다.
        memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);
        return memberRepository.getLastInsertId();
    }

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
