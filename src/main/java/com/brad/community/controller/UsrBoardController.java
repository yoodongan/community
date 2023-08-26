package com.brad.community.controller;

import com.brad.community.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usr/board")
@RequiredArgsConstructor
public class UsrBoardController {
    private final BoardService boardService;

}