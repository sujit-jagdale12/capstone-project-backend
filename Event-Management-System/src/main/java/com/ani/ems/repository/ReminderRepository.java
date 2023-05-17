package com.ani.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ani.ems.domain.ReminderUpdate;

public interface ReminderRepository extends JpaRepository<ReminderUpdate,Long>{
    
}
