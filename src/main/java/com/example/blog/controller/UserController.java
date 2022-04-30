package com.example.blog.controller;

import com.example.blog.entity.User;
import com.example.blog.service.BoardService;
import com.example.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final BoardService boardService;

    @GetMapping("/join")
    public String joinForm(Model model) {
        log.info("UserController.joinForm");
        model.addAttribute("user", new User());
        return "user/joinForm";
    }

    @GetMapping("/login")
    public String loginForm() {
        log.info("UserController.loginForm");
        return "user/loginForm";
    }

    @RequestMapping("/logout")
    public String logot(HttpSession session) {
        log.info("UserController.logout");
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/user/{id}/update")
    public String updateForm(@PathVariable Integer id, Model model) {
        User user = userService.showDetail(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 유저 정보가 존재하지 않습니다.");
        });
        model.addAttribute("user", user);
        return "user/updateForm";
    }
}
