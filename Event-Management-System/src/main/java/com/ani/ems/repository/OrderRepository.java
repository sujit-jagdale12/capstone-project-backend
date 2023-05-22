package com.ani.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ani.ems.domain.OrderTicket;

public interface OrderRepository extends JpaRepository<OrderTicket,Long>{
    
}
