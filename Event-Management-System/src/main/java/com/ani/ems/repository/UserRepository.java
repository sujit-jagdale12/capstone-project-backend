package com.ani.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ani.ems.domain.User;

public interface UserRepository extends JpaRepository<User,Long> {
    
}
