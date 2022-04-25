package com.example.blog.controller.api;

import com.example.blog.dto.ResponseDTO;
import com.example.blog.entity.User;
import com.example.blog.entity.UserRole;
import com.example.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserApiController {

    private final UserService userService;

    @PostMapping
    public ResponseDTO<Integer> save(@RequestBody User user) {
        log.info("UserApiController.save");
        int status = userService.join(user);
        return new ResponseDTO<Integer>(HttpStatus.OK.value(), status);
    }
}
