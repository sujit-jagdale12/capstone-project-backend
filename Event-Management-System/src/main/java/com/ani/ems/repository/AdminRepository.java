package com.ani.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ani.ems.domain.Event;

public interface AdminRepository extends JpaRepository<Event,Long> {
    
}
