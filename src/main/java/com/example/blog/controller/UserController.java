package com.example.blog.controller;

import com.example.blog.entity.User;
import com.example.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/join")
    public String joinForm(Model model) {
        log.info("UserController.joinForm");
        model.addAttribute("user", new User());
        return "joinForm";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute User user) {
        log.info("UserController.join");
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm() {
        log.info("UserController.loginForm");
    }
}
