package com.example.blog.repository;

import com.example.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsernameAndPassword(String username, String password);

    /*@Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
    Optional<User> login(String username, String password);*/
}
