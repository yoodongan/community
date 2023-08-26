package com.brad.community.repository;

import com.brad.community.vo.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BoardRepository {
    Board findById(@Param("boardId") Long boardId);
}