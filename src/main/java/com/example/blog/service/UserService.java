package com.example.blog.service;

import com.example.blog.entity.User;
import com.example.blog.entity.UserRole;
import com.example.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Transactional(readOnly = true)
    public User login(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Transactional(readOnly = true)
    public Optional<User> showDetail(Integer id) {
        return userRepository.findById(id);
    }

    @Transactional
    public User update(Integer id, User updateParam) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("해당 유저가 존재하지 않습니다.");
                });
        user.setPassword(updateParam.getPassword());
        user.setEmail(updateParam.getEmail());
        return user;
    }
}
