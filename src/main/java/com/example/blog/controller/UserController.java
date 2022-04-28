package com.example.blog.controller;

import com.example.blog.entity.User;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserRepository userRepository;
    private final BoardService boardService;

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

    @RequestMapping("/logout")
    public String loginForm(HttpSession session) {
        log.info("UserController.logout");
        session.invalidate();
        return "redirect:/";
    }
}
