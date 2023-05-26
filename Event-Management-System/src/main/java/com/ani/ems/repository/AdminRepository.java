package com.ani.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ani.ems.domain.Event;

import java.time.LocalDate;
import java.util.List;

public interface AdminRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByLocation(String location);

    List<Event> findAllByStartdate(LocalDate date);

    List<Event> findAllByEnddateGreaterThanEqual(LocalDate date);
}
