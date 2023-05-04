package com.ani.ems.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ani.ems.domain.Ticket;

public interface TicketRepository extends JpaRepository<Ticket,Long>{
    
    Optional<Ticket> findByType(String role);
}
