package com.ani.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ani.ems.domain.ReminderUpdate;

public interface ReminderRepository extends JpaRepository<ReminderUpdate,Long>{
    List<ReminderUpdate> findByEventId(Long id);
}
