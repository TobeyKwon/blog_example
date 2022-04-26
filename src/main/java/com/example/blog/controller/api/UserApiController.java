package com.example.blog.controller.api;

import com.example.blog.dto.ResponseDTO;
import com.example.blog.entity.User;
import com.example.blog.entity.UserRole;
import com.example.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@Slf4j
@RequiredArgsConstructor("/api/user")
@RestController
public class UserApiController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<Integer> save(@RequestBody User user) {
        log.info("UserApiController.save");
        int status = userService.join(user);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Integer> login(@RequestBody User user, HttpSession session) {
        log.info("UserApiController.login");
        User loginUser = userService.login(user);

        if(loginUser != null) {
            session.setAttribute("loginUser", loginUser);
            return new ResponseEntity<>(1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(-1, HttpStatus.OK);
        }
    }
}
