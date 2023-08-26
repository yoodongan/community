package com.brad.community.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class UsrHomeController {

    @RequestMapping("/usr/main")
    public String main() {
        return "/home/main";
    }
    @RequestMapping("/")
    public String root() {
        return "redirect:/usr/main";
    }
}
