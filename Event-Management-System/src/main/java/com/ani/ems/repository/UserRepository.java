package com.ani.ems.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ani.ems.domain.User;
import com.ani.ems.dto.EventListDto;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmailAndPassword(String email, String password);
    
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
    
}
