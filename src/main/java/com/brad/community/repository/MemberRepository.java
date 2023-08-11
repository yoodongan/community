package com.brad.community.repository;

import com.brad.community.vo.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberRepository {
    void join(@Param("loginId") String loginId, @Param("loginPw") String loginPw,
              @Param("name") String name, @Param("nickname") String nickname, @Param("cellphoneNo") String cellphoneNo, @Param("email") String email);

    Long getLastInsertId();

    Member findById(@Param("id") Long memberId);

    Member findByLoginId(@Param("loginId") String loginId);

    Member findByNameAndEmail(@Param("name") String name, @Param("email") String email);
}