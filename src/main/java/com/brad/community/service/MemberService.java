package com.brad.community.service;

import com.brad.community.repository.MemberRepository;
import com.brad.community.vo.DataResponse;
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
    public DataResponse join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
        // 로그인 아이디로 회원 찾아와서, 존재한다면 회원가입이 진행되면 안됨.
        boolean loginIdDup = isLoginIdDuplicated(loginId); // Dup은 Duplicated의 줄임
        boolean nameAndEmailDup = isDuplicatedWithNameAndMail(name, email);
        if(loginIdDup) return DataResponse.of("F-1", "이미 존재하는 로그인 ID 입니다!");
        if(nameAndEmailDup) return DataResponse.of("F-1", "이미 존재하는 이름-이메일 조합입니다!");
        memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);
        Long memberId = memberRepository.getLastInsertId();
        return DataResponse.of("S-1", "회원가입이 완료되었습니다!", memberId);
    }

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public DataResponse findByLoginId(String loginId) {
        Member member = memberRepository.findByLoginId(loginId);
        if(member == null) return DataResponse.of("F-1", "존재하지 않는 로그인 id 입니다.");
        return DataResponse.of("S-1", "존재하는 로그인 id입니다.", member);
    }
}
