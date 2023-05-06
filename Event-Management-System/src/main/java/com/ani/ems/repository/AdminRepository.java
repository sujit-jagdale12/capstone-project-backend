package com.ani.ems.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ani.ems.domain.Event;
import java.util.List;

public interface AdminRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByLocation(String location);

}
