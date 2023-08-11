package com.brad.community.service;

import com.brad.community.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
        memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);
    }
}
