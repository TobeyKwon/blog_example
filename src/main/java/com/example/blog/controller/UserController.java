package com.example.blog.controller;

import com.example.blog.entity.User;
import com.example.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/join")
    public String joinForm(Model model) {
        log.info("UserController.joinForm");
        model.addAttribute("user", new User());
        return "joinForm";
    }

    @GetMapping("/login")
    public String loginForm() {
        log.info("UserController.loginForm");
        return "loginForm";
    }
}
