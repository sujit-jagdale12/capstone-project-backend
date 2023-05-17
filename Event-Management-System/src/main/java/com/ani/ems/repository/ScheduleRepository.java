package com.ani.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ani.ems.domain.Schedule;

public interface ScheduleRepository extends JpaRepository <Schedule,Long> {

    
} 