package com.ani.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ani.ems.domain.Schedule;

public interface ScheduleRepository extends JpaRepository <Schedule,Long> {

    List<Schedule> findByEventId(Long eventId);
} 