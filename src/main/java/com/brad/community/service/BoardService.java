package com.brad.community.service;

import com.brad.community.repository.BoardRepository;
import com.brad.community.vo.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Board findById(Long boardId) {
        return boardRepository.findById(boardId);
    }
}
