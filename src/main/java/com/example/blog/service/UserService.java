package com.example.blog.service;

import com.example.blog.entity.User;
import com.example.blog.entity.UserRole;
import com.example.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public int join(User user) {
        try {
            user.setRole(UserRole.USER);
            userRepository.save(user);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("UserService: join: {}", e.getMessage());
        }
        return -1;
    }
}
