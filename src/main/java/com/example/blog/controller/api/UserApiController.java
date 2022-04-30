package com.example.blog.controller.api;

import com.example.blog.entity.Board;
import com.example.blog.entity.User;
import com.example.blog.service.BoardService;
import com.example.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserApiController {

    private final UserService userService;
    private final BoardService boardService;

    @PostMapping
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

    @PutMapping("/{id}")
    public ResponseEntity<Integer> update(@PathVariable Integer id, @RequestBody User updateParam, HttpSession session) {
        log.info("UserApiController.login");
        User user = userService.update(id, updateParam);
        session.invalidate();
        session.setAttribute("loginUser", user);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }
}
