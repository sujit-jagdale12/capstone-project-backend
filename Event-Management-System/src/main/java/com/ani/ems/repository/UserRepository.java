package com.ani.ems.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ani.ems.domain.User;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmailAndPassword(String email, String password);
    
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
    
}
